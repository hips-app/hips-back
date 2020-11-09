package com.hips.api.responses;

import java.io.Serializable;
import java.util.Date;

public class SaveCaloriesRequest implements Serializable {

    Double Calories;
    Date date;

    public SaveCaloriesRequest() {
        //this method is empty
    }

    public double getCalories() {
        return Calories;
    }

    public void setCalories(Double calories) {
        Calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
