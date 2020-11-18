package com.hips.api.controllers;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.models.ExerciseDataPoint;
import com.hips.api.models.UserAccount;
import com.hips.api.models.UserGoal;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.ExerciseDataPointRepository;
import com.hips.api.repositories.PlannedExerciseRepository;
import com.hips.api.repositories.UserAccountRepository;
import com.hips.api.repositories.UserGoalRepository;
import com.hips.api.responses.SaveExerciseSessionRequest;
import com.hips.api.services.PlannedExerciseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exercise")
public class ExerciseInformationController {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ExerciseDataPointRepository exerciseDataPointRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PlannedExerciseRepository plannedExerciseRepository;

    @Autowired
    private UserGoalRepository userGoalRepository;

    @Autowired
    private PlannedExerciseService plannedExerciseService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> saveCaloriesDataPoint(@RequestHeader("Authorization") String token, @PathVariable("id") int userId, @RequestBody SaveExerciseSessionRequest body){

        List<Integer> ids = body.getIds();
        Date date = body.getDate();

        if(ids == null || date == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Integer accId = Integer.parseInt(AuthenticationAssistant.getJWTSubject(jwtSecret, token));

        Account account = accountRepository.getById(accId);

        if(account.getType().getId() == 1 && accId != userId){

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserAccount userAccount = userAccountRepository.findByAccount(account);

        if(userAccount == null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ExerciseDataPoint> dataPoints = ids
                .stream()
                .map(id -> plannedExerciseRepository.getById(id))
                .map(exercise -> new ExerciseDataPoint(userAccount, exercise, date))
                .collect(Collectors.toList());

        exerciseDataPointRepository.saveAll(dataPoints);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/exercise-progress")
      public ResponseEntity<Integer> getExerciseProgress(
    @RequestHeader("Authorization") String token
  ) {
    if (token == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    Account account = AuthenticationAssistant.validateToken(accountRepository, jwtSecret, token);
    if (account == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    if (account.getType().getId() != 1) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    UserAccount userAccount = userAccountRepository.findByAccount(account);

    if(userAccount == null){

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    UserGoal userGoal = userGoalRepository.getByUserAccountId(userAccount.getId());
    if(userGoal== null){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    Date initialDate= userGoal.getCreatedAt();
    Date finalDate = userGoal.getExpirationDate();
    int plannedExercises = plannedExerciseService.getAllPlannedExercises(initialDate, finalDate);
    int checkedPlannedEcercises = plannedExerciseService.getCheckedPlannedExercises(initialDate, finalDate);
    int percent = (checkedPlannedEcercises * 100)/plannedExercises;
    return new ResponseEntity<Integer>(percent, HttpStatus.OK);
  }
}
