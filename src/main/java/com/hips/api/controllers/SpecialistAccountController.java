package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.UserGoalRepository;
import com.hips.api.repositories.UserMedicalDataRepository;
import com.hips.api.responses.ProfileResponse;
import com.hips.api.responses.SpecialistAccountResponse;
import com.hips.api.services.SpecialistAccountService;
import com.hips.api.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/specialist-users")
public class SpecialistAccountController {


    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private SpecialistAccountService specialistAccountService;


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserGoalRepository userGoalRepository;

    @Autowired
    private UserMedicalDataRepository userMedicalDataRepository;


    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getSpecialistAccounts(@RequestHeader("Authorization") String token){

        HttpStatus verifySpecialistToken= tokenAuthenticationService.verifySpecialistToken(token);
        if (verifySpecialistToken!= HttpStatus.OK) {
            return new ResponseEntity<>(verifySpecialistToken);
        }
        Integer specialistId;
        specialistId = specialistAccountService.getId(token);
        SpecialistAccount specialistAccount = specialistAccountService.findById(specialistId);

            List<UserAccount> specialistAccounts = specialistAccount.getUserAccounts();
            List<ProfileResponse> listUsers=new ArrayList<>();

            for (UserAccount userAccount : specialistAccounts) {
                Account account = userAccount.getAccount();

                if (account.getType().getId() != 1) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                }
                UserMedicalData userMedicalData = userMedicalDataRepository.getByUserAccountId(
                        userAccount.getId()
                );
                UserGoal userGoal = userGoalRepository.getByUserAccountId(userAccount.getId());

                ProfileResponse account1 = new ProfileResponse(account, userGoal, userMedicalData);
                listUsers.add(account1);
            }
        return new ResponseEntity<>( listUsers, HttpStatus.OK);

    }

}