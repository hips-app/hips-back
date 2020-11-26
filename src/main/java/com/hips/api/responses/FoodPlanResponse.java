package com.hips.api.responses;

import com.hips.api.models.FoodPlan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FoodPlanResponse implements Serializable {

    Date startDate;

    Date endDate;

    String description;

    List<FoodResponse> foodPlanList;

    public FoodPlanResponse(FoodPlan plan, List<FoodResponse> foodPlanList) {

        this.startDate = plan.getStartDate();
        this.endDate = plan.getEndDate();
        this.description = plan.getDescription();
        this.foodPlanList = foodPlanList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FoodResponse> getFoodPlanList() {
        return foodPlanList;
    }

    public void setFoodPlanList(List<FoodResponse> foodPlanList) {
        this.foodPlanList = foodPlanList;
    }
}
