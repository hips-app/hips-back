package com.hips.api.repositories;

import com.hips.api.models.DailyFoodPlan;
import com.hips.api.models.FoodPlan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyFoodPlanRepository extends CrudRepository<DailyFoodPlan, Integer> {

    List<DailyFoodPlan> findByFoodPlan(FoodPlan plan);
}
