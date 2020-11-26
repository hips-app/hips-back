package com.hips.api.services;

import com.hips.api.models.*;
import com.hips.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodPlanService {

    @Autowired
    UserGoalRepository goalRepository;

    @Autowired
    FoodPlanRepository planRepository;

    @Autowired
    DailyFoodPlanRepository dailyPlanRepository;

    @Autowired
    PlannedMealRepository mealRepository;

    @Autowired
    PlannedFoodRepository foodRepository;

    public FoodPlanService() {
    }

    public FoodPlan getFoodPlan(Account account){

        UserGoal userGoal = goalRepository.getByUserAccountId(account.getId());

        return planRepository.getByUserGoalId(userGoal.getId());
    }

    public List<PlannedFood> getPlannedFoods(FoodPlan plan){

        List<DailyFoodPlan> dailyPlans = dailyPlanRepository.findByFoodPlan(plan);

        return dailyPlans
                .stream()
                .flatMap((dailyPlan) -> mealRepository.findByDailyFoodPlan(dailyPlan).stream())
                .flatMap((meal) -> foodRepository.findByPlannedMeal(meal).stream())
                .collect(Collectors.toList());
    }
}
