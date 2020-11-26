package com.hips.api.controllers;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.models.SportPlan;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.repositories.SportPlanRepository;
import com.hips.api.responses.DailySportPlanRequest;
import com.hips.api.responses.StatisticsPOJO;
import com.hips.api.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/statistic")
public class UserStatisticsController {
    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SportPlanRepository sportPlanRepository;

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Fetches the data about goal advancement in terms of exercise, for the authenticated user.
     * @param token JWT for authentication.
     * @return http response with a list of entities detailing statistics over time.
     */
    @GetMapping("/exercise")
    public ResponseEntity< List<StatisticsPOJO>> getExerciseStatistics(
            @RequestHeader("Authorization") String token
    ) {
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account account = AuthenticationAssistant.validateToken(accountRepository, jwtSecret, token);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (account.getType().getId() != 1) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SportPlan sportPlan = sportPlanRepository.findTopByOrderByIdDesc();
        List<StatisticsPOJO> statistics = statisticsService.getUserStatistics(sportPlan.getId());

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
