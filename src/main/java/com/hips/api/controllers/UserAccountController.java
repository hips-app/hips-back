package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.LogInResponse;
import com.hips.api.responses.SelectExercisesResponse;
import com.hips.api.responses.UserGoalResponse;
import com.hips.api.services.TokenAuthenticationService;
import io.jsonwebtoken.*;

import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class UserAccountController {
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

 // @Autowired
 // private PhysicalExerciseRepository physicalExerciseRepository;

  @Autowired
  private UserGoalRepository userGoalRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @PostMapping("/signup")
  public ResponseEntity<LogInResponse> signUp(
    @RequestBody HashMap<String, String> req
  ) {
    String uid = UUID.randomUUID().toString();
    String name, lastname, email, pass;
    name = req.get("firstname");
    lastname = req.get("lastname");
    email = req.get("email");
    pass = req.get("password");

    if (name == null || lastname == null || email == null || pass == null) {
      return new ResponseEntity<>(new LogInResponse(), HttpStatus.BAD_REQUEST);
    }

    List<AccountType> aType = accountTypeRepository.findByName("User");

    String salt = BCrypt.gensalt();

    pass = BCrypt.hashpw(pass, salt);

    Account account = new Account(
      uid,
      aType.get(0),
      email,
      name,
      lastname,
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

    String token = createJWT(account.getId(), 1000 * 60 * 2);

    tokenRepository.save(new AccountTokenWhitelist(account, token));

    return new ResponseEntity<>(
      new LogInResponse(account, token),
      HttpStatus.OK
    );
  }

  @PostMapping("/login")
  public ResponseEntity<LogInResponse> login(
    @RequestBody HashMap<String, String> req
  ) {
    String email, password;
    email = req.get("email");
    password = req.get("password");
    Account account = null;
    try {
      account = accountRepository.findByEmail(email);
    } catch (Exception e) {
      return new ResponseEntity<>(
        new LogInResponse("The account doesn't esist"),
        HttpStatus.BAD_REQUEST
      );
    }
    if (BCrypt.checkpw(password, account.getPassword())) {
      String token = createJWT(account.getId(), 1000 * 60 * 60 * 2);
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

 // @GetMapping("/get_physical_exercise")
 // public ResponseEntity<SelectExercisesResponse> selectExercise(
 //         @RequestBody HashMap<String, String> req
 // ){
 //   PhysicalExercise physicalExercise = null;
 //   PhysicalExerciseType physicalExerciseType = null;
 //   int physicalExerciseTypeId;
 //   physicalExerciseTypeId = Integer.parseInt(req.get("physicalExerciseType"));


 //   return new ResponseEntity<>(
 //           new SelectExercisesResponse(physicalExercise), HttpStatus.OK );

 // }
  @PostMapping("/set_goal/{id}")
  public ResponseEntity<UserGoalResponse> setGoal(
          @PathVariable("id") int userId,
          @RequestBody HashMap<String, String> req
  ) throws ParseException {

    UserAccount userAccount;
    String description;
    Date expirationDate;
    String token;
    Account account;
    Integer tokId;

    description = req.get("description");
    expirationDate = new SimpleDateFormat("dd/MM/yyyy").parse(req.get("expiration_date"));
    token = req.get("token");


    if (description == null || expirationDate == null || token == null) {
      return new ResponseEntity<>(new UserGoalResponse(), HttpStatus.BAD_REQUEST);
    }


    try {

      tokId = Integer.parseInt(getJWT_Subject(token));


      if (!tokId.equals(userId)) {

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
      }

    }catch(ExpiredJwtException e){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
     }
    account = accountRepository.findById(userId);


    if(account.getType().getId() != 1){

      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    userAccount = userAccountRepository.findByAccount(account);
    UserGoal userGoal = new UserGoal(
            userAccount,
            description,
            expirationDate
    );
    userGoal = userGoalRepository.save(userGoal);
    return new ResponseEntity<>(new UserGoalResponse(userGoal), HttpStatus.OK);
  }

    @PostMapping("/logout")
    @Transactional
    public ResponseEntity<Void> logOut(@RequestBody HashMap<String, String> req){

        String token = req.get("token");

        if(token == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            tokenRepository.deleteByToken(token);
        }
        catch (SignatureException | ExpiredJwtException jwtException){

            jwtException.printStackTrace();
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/personal/{id}")
    @Transactional
    public ResponseEntity<Void> registerPersonalInfo(@PathVariable("id") int userId, @RequestBody HashMap<String, String> request){

        String token = request.get("token");

        if(token == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{

            Integer tokId = Integer.parseInt(getJWT_Subject(token));

            if(!tokId.equals(userId)){

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch (SignatureException | NumberFormatException | ExpiredJwtException e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Account account = accountRepository.getById(userId);

        if(account.getType().getId() != 1){

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String firstName = request.get("firstname");
        String lastName = request.get("lastname");
        String email = request.get("email");

        if(firstName != null) account.setFirstName(firstName);
        if(lastName != null) account.setLastName(lastName);
        if(email != null) account.setEmail(email);

        accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/personal-update/{id}")
    @Transactional
    public ResponseEntity<Void> updatePersonalInfo(@PathVariable("id") int userId, @RequestBody HashMap<String, String>req){

        String token = req.get("token");

        if(token == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{

            Integer tokId = Integer.parseInt(getJWT_Subject(token));

            if(!tokId.equals(userId)){

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch (SignatureException | NumberFormatException | ExpiredJwtException e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Account account = accountRepository.getById(userId);
        UserAccount userAccount = userAccountRepository.getByAccountId(userId);
        int userAccountId= userAccount.getId();
        UserMedicalData userMedicalData = userMedicalDataRepository.getByUserAccountId(userAccountId);

        if(account.getType().getId() != 1){

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String firstName = req.get("firstname");
        String lastName = req.get("lastname");
        int heightInCentimeters = Integer.parseInt(req.get("heightInCentimeters"));
        int weightInKilograms = Integer.parseInt(req.get("weightInKilograms"));

        if (firstName != null && lastName != null && heightInCentimeters!=0 && weightInKilograms!=0) {
            if (firstName != account.getFirstName()) {
                account.setFirstName(firstName);
            }
            if (lastName != account.getLastName()) {
                account.setLastName(lastName);
            }
            if (heightInCentimeters != userMedicalData.getHeightInCentimeters()) {
                userMedicalData.setHeightInCentimeters(heightInCentimeters);
            }
            if (weightInKilograms != userMedicalData.getWeightInKilograms()) {
                userMedicalData.setWeightInKilograms(weightInKilograms);
            }
        }
        accountRepository.save(account);
        userMedicalDataRepository.save(userMedicalData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/medical-data/{id}")
    public ResponseEntity<Void> registerMedicalData (@PathVariable ("id") int userId, @RequestBody HashMap< String,String> req ){
        String token= req.get("token");
        if (token==null) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            Integer tokId= Integer.parseInt(getJWT_Subject(token));
            if(!tokId.equals(userId)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch (SignatureException | NumberFormatException | ExpiredJwtException e){

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserAccount user= userAccountRepository.getByAccountId(userId);

        String birthDay=req.get("birthDay");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date=null;
            try {
                date = dateFormat.parse(birthDay);
            } catch (Exception e) {
                e.printStackTrace();
            }

        String heightInCentimeters=req.get("heightInCentimeters");
        String weightInKilograms= req.get("weightInKilograms");

        UserMedicalData medicaldata= new UserMedicalData(user, date, Integer.parseInt(heightInCentimeters),Integer.parseInt(weightInKilograms));

        userMedicalDataRepository.save(medicaldata);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    String getJWT_Subject(String token) throws SignatureException {

            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
                    .parseClaimsJws(token).getBody();

            return claims.getSubject();
        }

  public String createJWT(Integer id, long ttlMillis) {
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);

    System.out.println(JWT_SECRET);

    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
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
