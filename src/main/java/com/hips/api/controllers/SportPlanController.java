package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sport-plans")
public class SportPlanController {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Autowired
  private SportPlanRepository sportPlanRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @GetMapping
  public ResponseEntity<SportPlan> getLatest(
    @RequestHeader("Authorization") String token
  ) {
    SportPlan sportPlan = sportPlanRepository.findTopByOrderByIdDesc();
    return new ResponseEntity<>(sportPlan, HttpStatus.OK);
  }
}
