package com.hips.api.controllers;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.Account;
import com.hips.api.models.FoodPlan;
import com.hips.api.repositories.AccountRepository;
import com.hips.api.responses.FoodPlanResponse;
import com.hips.api.responses.FoodResponse;
import com.hips.api.services.FoodPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/food_plan")
public class FoodPlanController {

    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    @Autowired
    private AccountRepository accountRepository;

    private FoodPlanService planService = new FoodPlanService();

    /**
     * Allows an authorized user to fetch the food plan of a given user,
     * identifying the target with its account id, passed in the param userId.
     * An authorized user is the user being targeted itself, or any expert.
     * @param token JWT for authentication.
     * @param userId id of the account entity belonging to the plan's owner.
     * @return http response, containing a summary object of the user's food plan.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FoodPlanResponse> getPlan(@RequestHeader("Authorization") String token, @PathVariable("id") int userId){

        Integer accId = Integer.parseInt(AuthenticationAssistant.getJWTSubject(JWT_SECRET, token));

        Account account = accountRepository.getById(accId);

        if(account.getType().getId() == 1 && accId != userId){

            return  new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        FoodPlan plan = planService.getFoodPlan(account);

        List<FoodResponse> foods = planService.getPlannedFoods(plan)
                .stream()
                .map((food) -> new FoodResponse(food))
                .collect(Collectors.toList());

        FoodPlanResponse response = new FoodPlanResponse(plan, foods);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
