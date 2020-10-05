package com.hips.api.controllers;

import com.hips.api.models.Account;
import com.hips.api.models.AccountType;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.AccountTypeRepository;
import com.hips.api.repositories.UserAccountRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@RestController
public class UserAccountController {

    private static String JWT_SECRET = System.getenv("JWT_SECRET");

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody HashMap<String, String> req){

        String uid = UUID.randomUUID().toString();
        String name, lastname, email, pass;
        name = req.get("firstname");
        lastname = req.get("lastname");
        email = req.get("email");
        pass = req.get("password");

        if(name == null || lastname == null || email == null || pass == null){

            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }

        List<AccountType> aType = accountTypeRepository.findByName("User");

        pass = BCrypt.hashpw(pass, BCrypt.gensalt());

        Account account = new Account(uid, aType.get(0), email, name, lastname, pass, "");

        UserAccount userAccount = new UserAccount(account, null);

        try{
            userAccountRepository.save(userAccount);
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<String>("", HttpStatus.FORBIDDEN);
        }

        String token = createJWT(1000 * 60 * 2);

        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

    public static String createJWT(long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }
}
