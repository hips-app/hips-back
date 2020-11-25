package com.hips.api.repositories;

import com.hips.api.models.PlannedFood;
import com.hips.api.models.PlannedMeal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlannedFoodRepository extends CrudRepository<PlannedFood, Integer> {

    List<PlannedFood> findByPlannedMeal(PlannedMeal meal);
}
