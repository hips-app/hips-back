package com.hips.api.repositories;

import com.hips.api.models.SportPlan;
import com.hips.api.models.UserGoal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SportPlanRepository
  extends CrudRepository<SportPlan, Integer> {
    SportPlan findTopByOrderByIdDesc();

    SportPlan getByUserGoal(UserGoal userGoal);
  }
