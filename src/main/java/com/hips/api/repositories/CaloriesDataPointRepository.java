package com.hips.api.repositories;

import com.hips.api.models.CaloriesDataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CaloriesDataPointRepository extends CrudRepository<CaloriesDataPoint, Integer> {
}
