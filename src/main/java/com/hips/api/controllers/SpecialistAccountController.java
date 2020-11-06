package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.responses.SpecialistAccountResponse;
import com.hips.api.services.SpecialistAccountService;
import com.hips.api.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins =  "*")
@RequestMapping("/specialist-users")
public class SpecialistAccountController {


    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private SpecialistAccountService specialistAccountService;

    @GetMapping
    public ResponseEntity<SpecialistAccountResponse> getSpecialistAccounts(@RequestHeader("Authorization") String token){

        HttpStatus verifySpecialistToken= tokenAuthenticationService.verifySpecialistToken(token);
        if (verifySpecialistToken!= HttpStatus.OK) {
            return new ResponseEntity<>(verifySpecialistToken);
        }
            Integer specialistId;
            specialistId = specialistAccountService.getId(token);
            SpecialistAccount specialistAccount = specialistAccountService.findById(specialistId);


        return new ResponseEntity<>(new SpecialistAccountResponse(specialistAccount), HttpStatus.OK);
    }

}
