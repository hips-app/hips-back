package com.hips.api.responses;

import com.hips.api.models.Account;
import com.hips.api.models.UserGoal;
import com.hips.api.models.UserMedicalData;
import java.io.Serializable;
import java.util.*;

public class DailySportPlanRequest implements Serializable {

  Integer weekDayId;
  List<Integer> physicalExercises;

  DailySportPlanRequest() {}

  public Integer getWeekDayId() {
    return this.weekDayId;
  }

  public List<Integer> getPhysicalExercises() {
    return this.physicalExercises;
  }
}
