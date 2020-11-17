package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "daily_food_plan")
public class DailyFoodPlan extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    FoodPlan foodPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    WeekDay weekDay;

    @Column(length = 500)
    String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    Calendar date;

    public DailyFoodPlan() {
    }

    public DailyFoodPlan(FoodPlan foodPlan, WeekDay weekDay, String description, Calendar date) {
        this.foodPlan = foodPlan;
        this.weekDay = weekDay;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FoodPlan getFoodPlan() {
        return foodPlan;
    }

    public void setFoodPlan(FoodPlan foodPlan) {
        this.foodPlan = foodPlan;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
