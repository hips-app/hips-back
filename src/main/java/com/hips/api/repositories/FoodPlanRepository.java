package com.hips.api.repositories;

import com.hips.api.models.FoodPlan;
import org.springframework.data.repository.CrudRepository;

public interface FoodPlanRepository extends CrudRepository<FoodPlan, Integer> {

    public FoodPlan getByUserGoalId(Integer id);
}
