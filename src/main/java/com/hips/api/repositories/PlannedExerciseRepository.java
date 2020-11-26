package com.hips.api.repositories;
import com.hips.api.models.PlannedExercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlannedExerciseRepository extends JpaRepository<PlannedExercise, Integer> {

    PlannedExercise getById(int userId);

    @Query(value = "SELECT COUNT(case when pe.checked = true then pe.id end) * 100.0/COUNT(pe.id) AS percentage FROM PlannedExercise pe INNER JOIN DailySportPlan dsp ON dsp.id = pe.dailySportPlan.id WHERE dsp.sportPlan.id = :planId")
        double getUserExerciseProgress(@Param("planId") int planId);
}
