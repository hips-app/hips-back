package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Table(name = "planned_meal")
public class PlannedMeal extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    DailyFoodPlan dailyFoodPlan;

    Time timeOfDay;

    Integer calories;

    Boolean checked;

    public PlannedMeal() {
    }

    public PlannedMeal(DailyFoodPlan dailyFoodPlan, Time timeOfDay, Integer calories, Boolean checked) {
        this.dailyFoodPlan = dailyFoodPlan;
        this.timeOfDay = timeOfDay;
        this.calories = calories;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DailyFoodPlan getDailyFoodPlan() {
        return dailyFoodPlan;
    }

    public void setDailyFoodPlan(DailyFoodPlan dailyFoodPlan) {
        this.dailyFoodPlan = dailyFoodPlan;
    }

    public Time getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
