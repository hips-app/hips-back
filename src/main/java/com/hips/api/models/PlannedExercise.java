package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "planned_exercise")
public class PlannedExercise extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    DailySportPlan dailySportPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    PhysicalExercise physicalExercise;

    @Column()
    int repetitionsAmount;

    @Column()
    int seriesAmount;

    public PlannedExercise() {
    }

    public PlannedExercise(int id, DailySportPlan dailySportPlan, PhysicalExercise physicalExercise, int repetitionsAmount,int seriesAmount) {
        this.id = id;
        this.dailySportPlan = dailySportPlan;
        this.physicalExercise = physicalExercise;
        this.repetitionsAmount = repetitionsAmount;
        this.seriesAmount = seriesAmount;
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

    public PhysicalExercise getPhysicalExercise() {
        return physicalExercise;
    }

    public void setPhysicalExercise(PhysicalExercise physicalExercise) {
        this.physicalExercise = physicalExercise;
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

    @Override
    public String toString() {
        return "PlannedExercise{" +
                "id=" + id +
                ", dailySportPlan=" + dailySportPlan +
                ", dailySportPlan=" + physicalExercise +
                ", repetitionsAmount=" + repetitionsAmount +
                ", seriesAmount=" + seriesAmount +
                '}';
    }
}
