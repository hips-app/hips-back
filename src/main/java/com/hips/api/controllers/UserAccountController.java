package com.hips.api.controllers;

import com.hips.api.assistants.*;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserAccountController {
  static final String PARAM_FIRST_NAME = "firstName";
  static final String PARAM_LAST_NAME = "lastName";
  static final String PARAM_EMAIL = "email";
  static final String PARAM_PASSWORD = "password";

  @Value("${JWT_SECRET}")
  private String JWT_SECRET;

  @Autowired
  private UserAccountRepository userAccountRepository;

  @Autowired
  private UserMedicalDataRepository userMedicalDataRepository;

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @Autowired
  private AccountTokenWhitelistRepository tokenRepository;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private UserGoalRepository userGoalRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @PostMapping
  public ResponseEntity<LogInResponse> signup(
    @RequestBody HashMap<String, String> req
  ) {
    String uid = null;
    String firstName, lastName, email, pass;
    firstName = req.get(PARAM_FIRST_NAME);
    lastName = req.get(PARAM_LAST_NAME);
    email = req.get(PARAM_EMAIL);
    pass = req.get(PARAM_PASSWORD);

    if (
      firstName == null || lastName == null || email == null || pass == null
    ) {
      return new ResponseEntity<>(new LogInResponse(), HttpStatus.BAD_REQUEST);
    }

    List<AccountType> accountType = accountTypeRepository.findByName("User");
    String salt = BCrypt.gensalt();
    pass = BCrypt.hashpw(pass, salt);
    Account account = new Account(
      uid,
      accountType.get(0),
      email,
      firstName,
      lastName,
      pass,
      salt,
      ""
    );

    UserAccount userAccount = new UserAccount(account, null);

    try {
      userAccount = userAccountRepository.save(userAccount);
      account = userAccount.getAccount();
    } catch (DataIntegrityViolationException e) {
      return new ResponseEntity<>(new LogInResponse(), HttpStatus.FORBIDDEN);
    }

    String token = AuthenticationAssistant.createJWT(
      JWT_SECRET,
      account.getId(),
      (long)1000 * 60 * 2
    );

    tokenRepository.save(new AccountTokenWhitelist(account, token));
    return new ResponseEntity<>(
      new LogInResponse(account, token),
      HttpStatus.OK
    );
  }

  @PostMapping("/goals")
  public ResponseEntity<Void> setGoal(
    @RequestHeader("Authorization") String token,
    @RequestBody HashMap<String, String> req
  )
    throws Exception {
    Integer accountId;
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWT_Subject(JWT_SECRET, token)
        );
    } catch (ExpiredJwtException e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    String description;
    Date expirationDate;

    description = req.get("description");
    expirationDate =
      new SimpleDateFormat("yyyy-MM-dd").parse(req.get("expirationDate"));

    if (description == null || expirationDate == null || token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    UserAccount userAccount = userAccountRepository.findByAccount(account);
    UserGoal userGoal = new UserGoal(userAccount, description, expirationDate);
    userGoal = userGoalRepository.save(userGoal);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PatchMapping("/personal-data")
  @Transactional
  public ResponseEntity<Void> updatePersonalInfo(
    @RequestHeader("Authorization") String token,
    @RequestBody HashMap<String, String> req
  ) {
    Integer accountId;
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    UserAccount userAccount = userAccountRepository.getByAccountId(accountId);
    int userAccountId = userAccount.getId();
    UserMedicalData userMedicalData = userMedicalDataRepository.getByUserAccountId(
      userAccountId
    );

    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    String firstName = req.get(PARAM_FIRST_NAME);
    String lastName = req.get(PARAM_LAST_NAME);
    int heightInCentimeters = Integer.parseInt(
      req.get("heightInCentimeters") == null
        ? "0"
        : req.get("heightInCentimeters")
    );
    int weightInKilograms = Integer.parseInt(
      req.get("weightInKilograms") == null ? "0" : req.get("weightInKilograms")
    );
    String birthDate = req.get("birthDate");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = dateFormat.parse(birthDate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (
      firstName != null &&
      lastName != null &&
      heightInCentimeters != 0 &&
      weightInKilograms != 0 &&
      date != null
    ) {
      if (!account.getFirstName().equals(firstName)) {
        account.setFirstName(firstName);
      }
      if (!account.getLastName().equals(lastName)) {
        account.setLastName(lastName);
      }
      accountRepository.save(account);
      if (userMedicalData != null) {
        if (heightInCentimeters != userMedicalData.getHeightInCentimeters()) {
          userMedicalData.setHeightInCentimeters(heightInCentimeters);
        }
        if (weightInKilograms != userMedicalData.getWeightInKilograms()) {
          userMedicalData.setWeightInKilograms(weightInKilograms);
        }
        userMedicalDataRepository.save(userMedicalData);
      } else {
        UserMedicalData medicaldata = new UserMedicalData(
          userAccount,
          date,
          heightInCentimeters,
          weightInKilograms
        );
        userMedicalDataRepository.save(medicaldata);
      }
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @GetMapping("/{id}/profile")
  public ResponseEntity<ProfileResponse> checkProfile(
    @RequestHeader("Authorization") String token,
    @PathVariable("id") int userId
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId = Integer.parseInt(
        AuthenticationAssistant.getJWT_Subject(JWT_SECRET, token)
      );
      if (!accountId.equals(userId)) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    } catch (
      SignatureException | NumberFormatException | ExpiredJwtException e
    ) {
      e.printStackTrace();
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    UserAccount userAccount = userAccountRepository.getByAccountId(accountId);
    int userAccountId = userAccount.getId();

    UserMedicalData userMedicalData = userMedicalDataRepository.getByUserAccountId(
      userAccountId
    );
    UserGoal userGoal = userGoalRepository.getByUserAccountId(userAccountId);
    return new ResponseEntity<>(
      new ProfileResponse(account, userGoal, userMedicalData),
      HttpStatus.OK
    );
  }
}
