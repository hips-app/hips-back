package com.hips.api.services;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.repositories.AccountRepository;
import io.jsonwebtoken.*;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationService {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Autowired
  private AccountRepository accountRepository;

  public TokenAuthenticationService() {
    //this method is empty
  }

  public String getJWTSubject(String token) throws SignatureException {
    Claims claims = Jwts
      .parser()
      .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  public HttpStatus verifyUserToken(String token) {
    if (token == null) {
      return (HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
    } catch (Exception e) {
      return (HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return (HttpStatus.UNAUTHORIZED);
    }
    return (HttpStatus.OK);
  }

  public HttpStatus verifySpecialistToken(String tokenSpecialist) {
    if (tokenSpecialist == null) {
      return (HttpStatus.BAD_REQUEST);
    }
    Integer specialistId;

    try {
      specialistId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, tokenSpecialist)
        );
    } catch (Exception e) {
      return (HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(specialistId);

    if (account.getType().getId() != 2) {
      return (HttpStatus.UNAUTHORIZED);
    }
    return (HttpStatus.OK);
  }
}
