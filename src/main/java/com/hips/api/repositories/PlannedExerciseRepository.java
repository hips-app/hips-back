package com.hips.api.repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hips.api.models.PlannedExercise;
import com.hips.api.models.UserGoal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlannedExerciseRepository extends JpaRepository<PlannedExercise, Integer> {

    PlannedExercise getById(int userId);

    @Query(value = "select count(a) from PlannedExercise a where a.dailySportPlan IN (select b.id from DailySportPlan b where b.date >=:dateini and b.date <=:datefin)")
        int getPlannedExercise (@Param("dateini") Calendar dateini, @Param("datefin") Calendar datefini);
    @Query(value = "select count(a) from PlannedExercise a where a.checked=true and a.dailySportPlan IN (select b.id from DailySportPlan b where b.date >=:dateini and b.date <=:datefin)")
        int getCheckedPlannedExercise (@Param("dateini") Calendar dateini, @Param("datefin") Calendar datefini);
}
