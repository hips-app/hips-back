package com.hips.api.controllers;

import com.hips.api.assistants.*;
import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.LogInResponse;
import io.jsonwebtoken.*;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
  @Value("${JWT_SECRET}")
  private String JWT_SECRET;

  @Autowired
  private AccountTokenWhitelistRepository tokenRepository;

  @Autowired
  private AccountRepository accountRepository;

  @PostMapping
  public ResponseEntity<LogInResponse> login(
    @RequestBody HashMap<String, String> req
  ) {
    String email, password;
    email = req.get("email");
    password = req.get("password");
    Account account = null;
    account = accountRepository.findByEmail(email);
    if (account == null) {
      return new ResponseEntity<>(
        new LogInResponse("The account doesn't exist"),
        HttpStatus.BAD_REQUEST
      );
    }
    if (BCrypt.checkpw(password, account.getPassword())) {
      String token = AuthenticationAssistant.createJWT(
        JWT_SECRET,
        account.getId(),
        (long) 1000 * 60 * 60 * 2
      );
      tokenRepository.save(new AccountTokenWhitelist(account, token));
      return new ResponseEntity<>(
        new LogInResponse(account, token),
        HttpStatus.OK
      );
    }
    return new ResponseEntity<>(
      new LogInResponse("Incorrect password"),
      HttpStatus.BAD_REQUEST
    );
  }

  @DeleteMapping
  @Transactional
  public ResponseEntity<Void> logout(
    @RequestHeader("Authorization") String token
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      tokenRepository.deleteByToken(token);
    } catch (SignatureException | ExpiredJwtException jwtException) {
      jwtException.printStackTrace();
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/login-with-google")
  public ResponseEntity<LogInResponse> oauthLogin(
    @RequestBody HashMap<String, String> req
  ) {
    // TODO auth validation
    String email, uid;
    email = req.get("email");
    uid = req.get("uid");
    Account account = null;
    account = accountRepository.findByEmail(email);
    if (account == null) {
      return new ResponseEntity<>(
        new LogInResponse("The account doesn't exist"),
        HttpStatus.BAD_REQUEST
      );
    }
    if (account.getUid() == null) {
      //account.setUid(uid);
      //accountRepository.save(account);
    } else if (!account.getUid().equals(uid)) {
      return new ResponseEntity<>(
        new LogInResponse("Unautorized"),
        HttpStatus.BAD_REQUEST
      );
    }
    String token = AuthenticationAssistant.createJWT(
      JWT_SECRET,
      account.getId(),
      (long) 1000 * 60 * 60 * 2
    );
    tokenRepository.save(new AccountTokenWhitelist(account, token));
    return new ResponseEntity<>(
      new LogInResponse(account, token),
      HttpStatus.OK
    );
  }

  @PostMapping("/login-with-token")
  public ResponseEntity<LogInResponse> loginWithToken(
    @RequestHeader("Authorization") String token
  ) {
    // TODO token validation
    Integer accountId;
    if (token == null) {
      return new ResponseEntity<>(
        new LogInResponse("The account doesn't exist"),
        HttpStatus.BAD_REQUEST
      );
    }
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWT_Subject(JWT_SECRET, token)
        );
    } catch (
      SignatureException | NumberFormatException | ExpiredJwtException e
    ) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Account account = accountRepository.getById(accountId);
    return new ResponseEntity<>(
      new LogInResponse(account, token),
      HttpStatus.OK
    );
  }
}
