package com.hips.api.repositories;

import com.hips.api.models.PlannedExercise;
import org.springframework.data.repository.CrudRepository;

public interface PlannedExerciseRepository extends CrudRepository<PlannedExercise, Integer> {

    PlannedExercise getById(int userId);
}
