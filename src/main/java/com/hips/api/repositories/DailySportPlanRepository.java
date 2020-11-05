package com.hips.api.repositories;

import com.hips.api.models.*;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DailySportPlanRepository
  extends CrudRepository<DailySportPlan, Integer> {}
