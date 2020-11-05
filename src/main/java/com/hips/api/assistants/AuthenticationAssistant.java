package com.hips.api.assistants;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.LogInResponse;
import com.hips.api.responses.ProfileResponse;
import com.hips.api.responses.SelectExercisesResponse;
import com.hips.api.responses.UserGoalResponse;
import com.hips.api.services.TokenAuthenticationService;
import io.jsonwebtoken.*;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

public class AuthenticationAssistant {

  public static String getJWT_Subject(String secret, String token) throws SignatureException {
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
