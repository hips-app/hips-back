package com.hips.api.repositories;

import com.hips.api.models.UserGoal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserGoalRepository extends CrudRepository<UserGoal, Integer> {
     UserGoal getByUserAccountId(Integer userId);
}
