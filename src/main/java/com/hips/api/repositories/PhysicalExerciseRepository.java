package com.hips.api.repositories;

import com.hips.api.models.PhysicalExercise;
import com.hips.api.models.PhysicalExerciseType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PhysicalExerciseRepository extends CrudRepository<PhysicalExercise, Integer> {

    List<PhysicalExercise>findByType(PhysicalExerciseType type);

    List<PhysicalExercise>findAll();

}


