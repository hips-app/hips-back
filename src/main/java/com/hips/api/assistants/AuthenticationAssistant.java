package com.hips.api.assistants;

import io.jsonwebtoken.*;
import java.security.Key;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AuthenticationAssistant {

  private AuthenticationAssistant() {}

  public static String getJWTSubject(String secret, String token)
    throws RuntimeException {
    Claims claims = Jwts
      .parser()
      .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }

  public static String createJWT(String secret, Integer id, long ttlMillis) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

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

    if (ttlMillis > 0) {
      long expMillis = nowMillis + ttlMillis;
      Date exp = new Date(expMillis);
      builder.setExpiration(exp);
    }

    return builder.compact();
  }
}
