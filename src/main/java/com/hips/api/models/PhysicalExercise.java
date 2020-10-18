package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "physical_exercise")
public class PhysicalExercise extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToMany (fetch = FetchType.LAZY)
    List<PhysicalExerciseType> physicalExerciseType;

    int repetitionsAmount;

    int seriesAmount;

    int estimatedDuration;

    @Column(length = 100)
    String videoUrl;

    @Column(length = 100)
    String imageUrl;

    @Column(length = 500)
    String description;

    public PhysicalExercise() {
    }

    public PhysicalExercise(int id, List<PhysicalExerciseType> physicalExerciseType, int repetitionsAmount, int seriesAmount, int estimatedDuration, String videoUrl, String imageUrl, String description) {

        this.id = id;
        this.physicalExerciseType = physicalExerciseType;
        this.repetitionsAmount = repetitionsAmount;
        this.seriesAmount = seriesAmount;
        this.estimatedDuration = estimatedDuration;
        this.videoUrl = videoUrl;
        this.imageUrl = imageUrl;
        this.description = description;
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

    public int getRepetitionsAmount() {
        return repetitionsAmount;
    }

    public void setRepetitionsAmount(int repetitionsAmount) {
        this.repetitionsAmount = repetitionsAmount;
    }

    public int getSeriesAmount() {
        return seriesAmount;
    }

    public void setSeriesAmount(int seriesAmount) {
        this.seriesAmount = seriesAmount;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PhysicalExercise{" +
                "id=" + id +
                ", physicalExerciseType=" + physicalExerciseType +
                ", repetitionsAmount=" + repetitionsAmount +
                ", seriesAmount=" + seriesAmount +
                ", estimatedDuration=" + estimatedDuration +
                ", videoUrl='" + videoUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
