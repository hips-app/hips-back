package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "planned_exercise")
public class PlannedExercise extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    DailySportPlan dailySportPlan;

    public PlannedExercise() {
    }

    public PlannedExercise(int id, DailySportPlan dailySportPlan) {
        this.id = id;
        this.dailySportPlan = dailySportPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DailySportPlan getDailySportPlan() {
        return dailySportPlan;
    }

    public void setDailySportPlan(DailySportPlan dailySportPlan) {
        this.dailySportPlan = dailySportPlan;
    }

    @Override
    public String toString() {
        return "PlannedExercise{" +
                "id=" + id +
                ", dailySportPlan=" + dailySportPlan +
                '}';
    }
}
