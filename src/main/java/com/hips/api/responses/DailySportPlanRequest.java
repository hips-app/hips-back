package com.hips.api.responses;

import java.io.Serializable;
import java.util.*;

public class DailySportPlanRequest implements Serializable {

  Integer weekDayId;
  private List<Integer> physicalExercises;

  DailySportPlanRequest() {}

  public Integer getWeekDayId() {
    return this.weekDayId;
  }

  public List<Integer> getPhysicalExercises() {
    return this.physicalExercises;
  }
}
