package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "exercise_data_point")
public class ExerciseDataPoint extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;

    @ManyToOne(fetch = FetchType.EAGER)
    UserAccount userAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    PlannedExercise plannedExercise;

    Date date;

    public ExerciseDataPoint() {
    }

    public ExerciseDataPoint(UserAccount userAccount, PlannedExercise plannedExercise, Date date) {
        this.userAccount = userAccount;
        this.plannedExercise = plannedExercise;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlannedExercise getPlannedExercise() {
        return plannedExercise;
    }

    public void setPlannedExercise(PlannedExercise plannedExercise) {
        this.plannedExercise = plannedExercise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
