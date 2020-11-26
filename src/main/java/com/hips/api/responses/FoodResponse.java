package com.hips.api.responses;

import com.hips.api.models.PlannedFood;

import java.io.Serializable;
import java.sql.Time;

public class FoodResponse implements Serializable {

    String type;
    String typeDescription;

    Integer amountInGrams;
    String description;

    Time timeOfDay;
    Integer calories;

    Boolean checked;

    public FoodResponse(PlannedFood food){

        this.type = food.getFoodType().getName();
        this.typeDescription = food.getFoodType().getDescription();
        this.amountInGrams = food.getAmountInGrams();
        this.description = food.getDescription();
        this.timeOfDay = food.getPlannedMeal().getTimeOfDay();
        this.calories = food.getPlannedMeal().getCalories();
        this.checked = food.getPlannedMeal().getChecked();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Integer getAmountInGrams() {
        return amountInGrams;
    }

    public void setAmountInGrams(Integer amountInGrams) {
        this.amountInGrams = amountInGrams;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
