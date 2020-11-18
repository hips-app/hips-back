package com.hips.api.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.PlannedExerciseRepository;

import org.slf4j.Logger;



@RestController
@EnableJpaAuditing
@CrossOrigin(origins = "*")

public class ApiController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PlannedExerciseRepository plannedExerciseRepository;

    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @RequestMapping()
    public String home() {
        return "Welcome to hips app";
    }
}
