package com.hips.api.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;



@RestController
@EnableJpaAuditing
@CrossOrigin(origins = "*")
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @RequestMapping()
    public String home() {
        return "Welcome to hips app";
    }
}
