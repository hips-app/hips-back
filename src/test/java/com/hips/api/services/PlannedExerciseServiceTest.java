package com.hips.api.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PlannedExerciseServiceTest {

    @Autowired
    private PlannedExerciseService plannedExerciseService;

    @Test
    void UserExerciseProgress(){
        double percent =(double)(17*100)/28;
        double expected = plannedExerciseService.getUserExerciseProgress(1);
        Assertions.assertEquals(percent,expected);
    }
}
