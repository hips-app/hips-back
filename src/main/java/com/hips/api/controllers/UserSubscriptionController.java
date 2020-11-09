package com.hips.api.controllers;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.models.SpecialistType;
import com.hips.api.models.SubscriptionType;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.responses.SpecialistAccountResponse;
import com.hips.api.responses.SubscriptionTypeResponse;
import com.hips.api.services.SpecialistAccountService;
import com.hips.api.services.SubscriptionTypeService;
import com.hips.api.services.TokenAuthenticationService;
import com.hips.api.services.UserAccountService;
import com.hips.api.services.UserSubscriptionService;
import io.jsonwebtoken.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user-subscripcion")
public class UserSubscriptionController {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Autowired
  private SpecialistAccountService specialistAccountService;

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private UserAccountService userAccountService;

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;

  @Autowired
  private SubscriptionTypeService subscriptionTypesService;

  @Autowired
  private UserSubscriptionService userSubscriptionService;

  @PostMapping(value = "/{id}/payment-method")
  public ResponseEntity<Void> payment(
    @RequestHeader("Authorization") String token,
    @RequestBody Map<String, Boolean> req,
    @PathVariable("id") int userId
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
      if (!accountId.equals(userId)) {
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);
    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    UserAccount userAccount = userAccountService.findByAccount(account);
    if (Boolean.TRUE.equals(userAccountService.hasPayment(userAccount))) {
      return new ResponseEntity<>(HttpStatus.FOUND);
    }
    userAccount.setPaymentMethod(req.get("paymentMethod"));
    userAccountService.save(userAccount);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/select-subscription")
  public ResponseEntity<Void> selectSubscription(
    @RequestHeader("Authorization") String token,
    @RequestBody Map<String, String> req
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    SubscriptionType subscriptionType = subscriptionTypesService.findById(
      Integer.parseInt(req.get("subscriptionTypeId"))
    );
    SpecialistAccount specialistAccount = specialistAccountService.findById(
      Integer.parseInt(req.get("specialistAccountId"))
    );
    UserAccount userAccount = userAccountService.findByAccount(account);
    if (!Boolean.TRUE.equals(userAccountService.hasPayment(userAccount))) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    if (userAccount.getSpecialistAccount() == null) {
      userAccount.setSpecialistAccount(specialistAccount);
      userAccountService.save(userAccount);
    } else {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    String expirationDate = req.get("expirationDate");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = dateFormat.parse(expirationDate);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    userSubscriptionService.createSubscription(
      userAccount,
      subscriptionType,
      date
    );
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping(value = "/subscription-types")
  public ResponseEntity<List<SubscriptionTypeResponse>> getSubscriptionTypes(
    @RequestHeader("Authorization") String token
  ) {
    HttpStatus verifyToken = tokenAuthenticationService.verifyUserToken(token);
    if (verifyToken != HttpStatus.OK) {
      return new ResponseEntity<>(verifyToken);
    }
    List<SubscriptionType> subscriptionTypes = subscriptionTypesService.findAll();
    List<SubscriptionTypeResponse> subscriptionTypeResponses = new ArrayList<>();
    subscriptionTypes.forEach(
      e -> subscriptionTypeResponses.add(new SubscriptionTypeResponse(e))
    );
    return new ResponseEntity<>(subscriptionTypeResponses, HttpStatus.OK);
  }

  @GetMapping(value = "/profesionales")
  public ResponseEntity<List<SpecialistAccountResponse>> getSpecialistAccounts(
    @RequestHeader("Authorization") String token
  ) {
    HttpStatus verifyToken = tokenAuthenticationService.verifyUserToken(token);
    if (verifyToken != HttpStatus.OK) {
      return new ResponseEntity<>(verifyToken);
    }
    List<SpecialistAccount> specialistAccounts = specialistAccountService.findAll();
    List<SpecialistAccountResponse> listSpecialist = new ArrayList<>();

    for (SpecialistAccount specialistAccount : specialistAccounts) {
      Account account1 = specialistAccount.getAccount();
      SpecialistType specialistType = specialistAccount.getType();
      SpecialistAccountResponse specialist1 = new SpecialistAccountResponse(
        account1,
        specialistType,
        specialistAccount
      );
      listSpecialist.add(specialist1);
    }
    return new ResponseEntity<>(listSpecialist, HttpStatus.OK);
  }

  @PostMapping(value = "/escoger-profesional")
  public ResponseEntity<Void> escogerProfesional(
    @RequestHeader("Authorization") String token,
    @RequestBody Map<String, String> req
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Integer accountId;
    try {
      accountId =
        Integer.parseInt(
          AuthenticationAssistant.getJWTSubject(jwtSecret, token)
        );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    Account account = accountRepository.getById(accountId);

    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    UserAccount userAccount = userAccountService.findByAccount(account);
    if (!Boolean.TRUE.equals(userAccountService.hasPayment(userAccount))) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    Integer specialistAccountId = Integer.parseInt(
      req.get("specialistAccountId")
    );
    SpecialistAccount specialistAccount = specialistAccountService.findById(
      specialistAccountId
    );
    if (userAccount.getSpecialistAccount() == null) {
      userAccount.setSpecialistAccount(specialistAccount);
    } else {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    userAccountService.save(userAccount);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
