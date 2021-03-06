package com.hips.api.controllers;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.models.CaloriesDataPoint;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.CaloriesDataPointRepository;
import com.hips.api.repositories.UserAccountRepository;
import com.hips.api.responses.SaveCaloriesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/nutrition")
public class NutritionalInformationController {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private CaloriesDataPointRepository caloriesDataPointRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/calories/{id}")
    public ResponseEntity<Void> saveCaloriesDataPoint(@RequestHeader("Authorization") String token, @PathVariable("id") int userId, @RequestBody SaveCaloriesRequest body){

        Double calories = body.getCalories();
        Date date = body.getDate();

        if(calories == null || date == null){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Account account;

        Integer accId = Integer.parseInt(AuthenticationAssistant.getJWTSubject(jwtSecret, token));

        account = accountRepository.getById(accId);

        if(account.getType().getId() == 1 && accId != userId){

            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UserAccount userAccount = userAccountRepository.findByAccount(account);

        if(userAccount == null){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CaloriesDataPoint dataPoint = new CaloriesDataPoint(userAccount, calories, date);

        caloriesDataPointRepository.save(dataPoint);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
