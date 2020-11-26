package com.hips.api.services;

import com.hips.api.models.PhysicalExerciseType;
import com.hips.api.repositories.PhysicalExerciseRepository;
import org.springframework.stereotype.Service;


@Service
public class SelectExercisesService {
    PhysicalExerciseRepository physicalExerciseRepository;

    public void selectExercise(PhysicalExerciseType type){
        physicalExerciseRepository.findByType(type);
    }

}
