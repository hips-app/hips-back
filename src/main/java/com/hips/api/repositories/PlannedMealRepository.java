package com.hips.api.repositories;

import com.hips.api.models.DailyFoodPlan;
import com.hips.api.models.PlannedMeal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlannedMealRepository extends CrudRepository<PlannedMeal, Integer> {

    public List<PlannedMeal> findByDailyFoodPlan(DailyFoodPlan plan);
}
