package com.hips.api.services;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.String;
import javax.xml.bind.DatatypeConverter;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.repositories.AccountRepository;


@Service
public class TokenAuthenticationService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    private AccountRepository accountRepository;

    public TokenAuthenticationService() {
    }

    public String getJWT_Subject(String token) throws SignatureException {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public HttpStatus verifyUserToken(String token) {

      if (token == null) {
      return (HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId = Integer.parseInt(
        AuthenticationAssistant.getJWT_Subject(jwtSecret, token)
      );
    } catch (
      SignatureException | NumberFormatException | ExpiredJwtException e
    ) {
      e.printStackTrace();
      return (HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return (HttpStatus.UNAUTHORIZED);
    }
        return (HttpStatus.OK);
    }

}
