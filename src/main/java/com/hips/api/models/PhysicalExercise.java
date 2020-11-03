package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "physical_exercise")
public class PhysicalExercise extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;

    @ManyToOne (fetch = FetchType.LAZY)
    PhysicalExerciseType type;

    @Column(length = 100)
    String name;

    @Column(length = 100)
    String videoUrl;

    @Column(length = 100)
    String imageUrl;

    @Column(length = 500)
    String description;

    public PhysicalExercise() {
    }

    public PhysicalExercise(int id, PhysicalExerciseType type, String name, String videoUrl, String imageUrl, String description) {

        this.id = id;
        this.type = type;
        this.name = name;
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

    public PhysicalExerciseType getType() {
        return type;
    }

    public void setType(PhysicalExerciseType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", physicalExerciseType=" + type +
                ", name=" + name +
                ", videoUrl='" + videoUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
