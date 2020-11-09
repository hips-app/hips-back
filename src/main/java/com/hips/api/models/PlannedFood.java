package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "planned_food")
public class PlannedFood extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    PlannedMeal plannedMeal;

    @ManyToOne(fetch = FetchType.LAZY)
    FoodType foodType;

    Integer amountInGrams;

    @Column(length = 500)
    String description;

    public PlannedFood() {
    }

    public PlannedFood(PlannedMeal plannedMeal, FoodType foodType, Integer amountInGrams, String description) {
        this.plannedMeal = plannedMeal;
        this.foodType = foodType;
        this.amountInGrams = amountInGrams;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlannedMeal getPlannedMeal() {
        return plannedMeal;
    }

    public void setPlannedMeal(PlannedMeal plannedMeal) {
        this.plannedMeal = plannedMeal;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
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
}
