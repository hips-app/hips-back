package com.hips.api.responses;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaveExerciseSessionRequest implements Serializable {

    List<Integer> ids;
    Date date;

    public SaveExerciseSessionRequest() {
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
