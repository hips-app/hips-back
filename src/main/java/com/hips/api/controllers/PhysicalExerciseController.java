package com.hips.api.controllers;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import com.hips.api.services.TokenAuthenticationService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pysical-exercises")
public class PhysicalExerciseController {

  @Value("${JWT_SECRET}")
  private String jwtSecret;

  @Autowired
  private PhysicalExerciseRepository physicalExerciseRepository;

  TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationService();

  /**
   * Gets all physical exercises, used as templates by the users to plan their
   * routines.
   * @return http response with a list of PhysicalExercise entities.
   */
  @GetMapping
  public ResponseEntity<List<PhysicalExercise>> getAll() {
    List<PhysicalExercise> exercises = physicalExerciseRepository.findAll();
    return new ResponseEntity<>(exercises, HttpStatus.OK);
  }
}
