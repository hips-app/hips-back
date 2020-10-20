package com.hips.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.String;
import javax.xml.bind.DatatypeConverter;


@Service
public class TokenAuthenticationService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    public TokenAuthenticationService() {
    }

    public String getJWT_Subject(String token) throws SignatureException {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }


}
