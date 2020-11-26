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
  private String jwtSecret;

  private static final String ACCOUNTERROR = "The account doesn't exist";

  @Autowired
  private AccountTokenWhitelistRepository tokenRepository;

  @Autowired
  private AccountRepository accountRepository;

  /**
   * Allows an existing user to login via email+password.
   * @param req request object that should hold the user's credentials
   * @return http response with object holding the user's personal information
   * and JWT for the session. Alternatively, an error message as string.
   */
  @PostMapping
  public ResponseEntity<LogInResponse> login(
    @RequestBody Map<String, String> req
  ) {
    String email;
    String password;
    email = req.get("email");
    password = req.get("password");
    Account account = null;
    account = accountRepository.findByEmail(email);
    if (account == null) {
      return new ResponseEntity<>(
        new LogInResponse(ACCOUNTERROR),
        HttpStatus.BAD_REQUEST
      );
    }
    if (BCrypt.checkpw(password, account.getPassword())) {
      String token = AuthenticationAssistant.createJWT(
        jwtSecret,
        account.getId(),
        10
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

  /**
   * Deletes a given token from the db's whitelist, thus rendering it invalid.
   * @param token should be valid JWT
   * @return http response, without payload
   */
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
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/login-with-google")
  public ResponseEntity<LogInResponse> oauthLogin(
    @RequestBody Map<String, String> req
  ) {
    String email;
    String uid;
    email = req.get("email");
    uid = req.get("uid");
    Account account = null;
    account = accountRepository.findByEmail(email);
    if (account == null) {
      return new ResponseEntity<>(
        new LogInResponse(ACCOUNTERROR),
        HttpStatus.BAD_REQUEST
      );
    }
    if (account.getUid() == null) {
      account.setUid(uid);
      accountRepository.save(account);
    } else if (!account.getUid().equals(uid)) {
      return new ResponseEntity<>(
        new LogInResponse("Unautorized"),
        HttpStatus.BAD_REQUEST
      );
    }
    String token = AuthenticationAssistant.createJWT(
      jwtSecret,
      account.getId(),
      10
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
    Integer accountId;
    if (token == null) {
      return new ResponseEntity<>(
        new LogInResponse(ACCOUNTERROR),
        HttpStatus.BAD_REQUEST
      );
    }
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Account account = accountRepository.getById(accountId);
    if (account == null) {
      return new ResponseEntity<>(
        new LogInResponse(ACCOUNTERROR),
        HttpStatus.BAD_REQUEST
      );
    }
    return new ResponseEntity<>(
      new LogInResponse(account, token),
      HttpStatus.OK
    );
  }
}
