package com.hips.api.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;

import com.hips.api.repositories.AccountRepository;

import org.slf4j.Logger;



@RestController
@EnableJpaAuditing
@CrossOrigin(origins = "*")

public class ApiController {
    @Autowired
    private AccountRepository accountRepository;
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @RequestMapping()
    public String home() {
        int valor = accountRepository.valor();
        return valor+"";
        //return "Welcome to hips app";
    }
}
