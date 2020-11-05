package com.hips.api.controllers;

import com.hips.api.assistants.*;
import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.responses.LogInResponse;
import com.hips.api.responses.ProfileResponse;
import com.hips.api.responses.SelectExercisesResponse;
import com.hips.api.responses.UserGoalResponse;
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
@RequestMapping("/pysical-exercises")
public class PhysicalExerciseController {

  @Value("${JWT_SECRET}")
  private String JWT_SECRET;

  @Autowired
  private PhysicalExerciseRepository physicalExerciseRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  @GetMapping
  public ResponseEntity<List<PhysicalExercise>> getAll() {
    List<PhysicalExercise> exercises = physicalExerciseRepository.findAll();
    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }
}
