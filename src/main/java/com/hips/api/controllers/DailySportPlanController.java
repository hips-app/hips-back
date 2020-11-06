package com.hips.api.controllers;

import com.hips.api.assistants.*;
import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.*;
import com.hips.api.services.TokenAuthenticationService;
import io.jsonwebtoken.*;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/daily-sport-plan")
public class DailySportPlanController {

  @Value("${JWT_SECRET}")
  private String JWT_SECRET;

  @Autowired
  private SportPlanRepository sportPlanRepository;

  @Autowired
  private DailySportPlanRepository dailySportPlanRepository;

  @Autowired
  private WeekDayRepository weekDayRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @PostMapping
  public ResponseEntity<SportPlan> register(
    @RequestHeader("Authorization") String token,
    @RequestBody DailySportPlanRequest dailySportPlanRequest
  ) {
    SportPlan sportPlan = sportPlanRepository.findTopByOrderByIdDesc();
    WeekDay weekDay = weekDayRepository.findById((int)dailySportPlanRequest.getWeekDayId());
    
    return new ResponseEntity<>(sportPlan, HttpStatus.OK);
  }
}
