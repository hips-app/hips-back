package com.hips.api.repositories;

import com.hips.api.models.ExerciseDataPoint;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseDataPointRepository extends CrudRepository<ExerciseDataPoint, Integer> {

}
