package com.hips.api.responses;

import com.hips.api.models.PhysicalExercise;
import com.hips.api.models.PhysicalExerciseType;

import java.io.Serializable;
import java.util.List;

public class SelectExercisesResponse  implements Serializable {

    int id;
    List<PhysicalExerciseType> physicalExerciseType;
    String description;

    public SelectExercisesResponse() {
    }

    public SelectExercisesResponse(PhysicalExercise physicalExercise) {
        this.id = physicalExercise.getId();
        this.physicalExerciseType = physicalExercise.getPhysicalExerciseType();
        this.description = physicalExercise.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<PhysicalExerciseType> getPhysicalExerciseType() {
        return physicalExerciseType;
    }

    public void setPhysicalExerciseType(List<PhysicalExerciseType> physicalExerciseType) {
        this.physicalExerciseType = physicalExerciseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
