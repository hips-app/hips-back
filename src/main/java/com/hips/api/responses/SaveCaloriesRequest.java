package com.hips.api.responses;

import java.io.Serializable;
import java.util.Date;

public class SaveCaloriesRequest implements Serializable {

  Double calories;
  Date date;

  public double getCalories() {
    return calories;
  }

  public void setCalories(Double calories) {
    this.calories = calories;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
