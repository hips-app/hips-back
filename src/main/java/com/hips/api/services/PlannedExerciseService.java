package com.hips.api.services;

import com.hips.api.repositories.PlannedExerciseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannedExerciseService {

    @Autowired
    private PlannedExerciseRepository plannedExerciseRepository;

    public double getUserExerciseProgress(int sportPlanId) {
        double percent = 0;
        try {
            percent = plannedExerciseRepository.getUserExerciseProgress(sportPlanId);
        } catch (ArithmeticException e) {
            percent = -1;
        }
        return percent;
    }

}
