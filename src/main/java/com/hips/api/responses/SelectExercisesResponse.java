package com.hips.api.responses;

import com.hips.api.models.PhysicalExercise;
import com.hips.api.models.PhysicalExerciseType;

import java.io.Serializable;
import java.util.List;

public class SelectExercisesResponse  implements Serializable {

    int id;
    PhysicalExerciseType type;
    String description;

    public SelectExercisesResponse() {
    }

    public SelectExercisesResponse(PhysicalExercise physicalExercise) {
        this.id = physicalExercise.getId();
        this.type = physicalExercise.getType();
        this.description = physicalExercise.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PhysicalExerciseType getType() {
        return type;
    }

    public void settype(PhysicalExerciseType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
