package com.hips.api.controllers;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;



@RestController
@EnableJpaAuditing
public class ApiController {
    Logger logger = LoggerFactory.getLogger(ApiController.class);
    @RequestMapping("/")
    public String home() {
        return "Hello World!";
    }
}
