package com.hips.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;


@Service
public class TokenAuthenticationService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    public TokenAuthenticationService() {
        //constructor vacio
    }

    public String getJwtSubject(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }


}
