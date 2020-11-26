package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.*;
import com.hips.api.services.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/daily-sport-plan")
public class DailySportPlanController {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Autowired
  private SportPlanRepository sportPlanRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @PostMapping
  public ResponseEntity<SportPlan> register(
    @RequestHeader("Authorization") String token,
    @RequestBody DailySportPlanRequest dailySportPlanRequest
  ) {
    SportPlan sportPlan = sportPlanRepository.findTopByOrderByIdDesc();

    return new ResponseEntity<>(sportPlan, HttpStatus.OK);
  }
}
