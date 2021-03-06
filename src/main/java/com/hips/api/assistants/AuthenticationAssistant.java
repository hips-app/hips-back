package com.hips.api.assistants;

import com.hips.api.models.Account;
import com.hips.api.repositories.AccountRepository;
import io.jsonwebtoken.*;
import java.security.Key;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AuthenticationAssistant {

  private AuthenticationAssistant() {}

  public static String getJWTSubject(String secret, String token) {
    try {
      Claims claims = Jwts
        .parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
        .parseClaimsJws(token)
        .getBody();

      return claims.getSubject();
    } catch (Exception e) {
      return null;
    }
  }

  public static Account validateToken(
    AccountRepository accountRepository,
    String jwtSecret,
    String token
  ) {
    Integer accountId;
    if (token == null) {
      return null;
    }
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
      return accountRepository.getById(accountId);
    } catch (Exception e) {
      return null;
    }
  }

  public static Account validateTokenAndUser(
    AccountRepository accountRepository,
    String jwtSecret,
    String token,
    int userId
  ) {
    Integer accountId;
    if (token == null) {
      return null;
    }
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );

      if (!accountId.equals(userId)) {
        return null;
      }
      return accountRepository.getById(accountId);
    } catch (Exception e) {
      return null;
    }
  }

  public static String createJWT(String secret, Integer id, long hours) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    Date now = new Date();

    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
    Key signingKey = new SecretKeySpec(
      apiKeySecretBytes,
      signatureAlgorithm.getJcaName()
    );

    JwtBuilder builder = Jwts
      .builder()
      .setIssuedAt(now)
      .setSubject(id.toString())
      .signWith(signatureAlgorithm, signingKey);

    if (hours > 0) {
      Date exp = Date.from(new Date().toInstant().plusSeconds(hours * 60 * 60));
      builder.setExpiration(exp);
    }

    return builder.compact();
  }
}
