package com.hips.api.controllers;

import com.hips.api.models.Account;
import com.hips.api.models.AccountTokenWhitelist;
import com.hips.api.models.AccountType;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.AccountTokenWhitelistRepository;
import com.hips.api.repositories.AccountTypeRepository;
import com.hips.api.repositories.UserAccountRepository;

import com.hips.api.responses.LogInResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class UserAccountController {

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountTokenWhitelistRepository tokenRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/signup")
    public ResponseEntity<LogInResponse> signUp(@RequestBody HashMap<String, String> req){

        String uid = UUID.randomUUID().toString();
        String name, lastname, email, pass;
        name = req.get("firstname");
        lastname = req.get("lastname");
        email = req.get("email");
        pass = req.get("password");

        if(name == null || lastname == null || email == null || pass == null){

            return new ResponseEntity<>(new LogInResponse(), HttpStatus.BAD_REQUEST);
        }

        List<AccountType> aType = accountTypeRepository.findByName("User");

        String salt = BCrypt.gensalt();

        pass = BCrypt.hashpw(pass, salt);

        Account account = new Account(uid, aType.get(0), email, name, lastname, pass, salt, "");

        UserAccount userAccount = new UserAccount(account, null);

        try{
            userAccount = userAccountRepository.save(userAccount);
            account = userAccount.getAccount();
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new LogInResponse(), HttpStatus.FORBIDDEN);
        }

        String token = createJWT(account.getId() ,1000 * 60 * 2);

        tokenRepository.save(new AccountTokenWhitelist(account, token));

        return new ResponseEntity<>(new LogInResponse(account, token), HttpStatus.OK);
    }
    @PostMapping ("/login")
    public  ResponseEntity<LogInResponse> login(@RequestBody HashMap<String, String> req)  {
        String  email, password;
        email = req.get("email");
        password = req.get("password");
        Account account = null;
        try {
            account = accountRepository.findByEmail(email);
        } catch (Exception e) {
            return new ResponseEntity<>(new LogInResponse("The account doesn't esist"), HttpStatus.BAD_REQUEST);
        } 
        if (BCrypt.checkpw(password, account.getPassword())){
            String token = createJWT(account.getId() ,1000 * 60 * 2);
            tokenRepository.save(new AccountTokenWhitelist(account, token));
            return new ResponseEntity<>(new LogInResponse(account,token), HttpStatus.OK);
        }
        return new ResponseEntity<>(new LogInResponse("Incorrect password"), HttpStatus.BAD_REQUEST);

    }

    public String createJWT(Integer id, long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        System.out.println(JWT_SECRET);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
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
