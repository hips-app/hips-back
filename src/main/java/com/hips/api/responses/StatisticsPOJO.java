package com.hips.api.responses;

import java.util.GregorianCalendar;

public class StatisticsPOJO {

    private Integer id;
    private GregorianCalendar date;
    private Double percentage;

    public StatisticsPOJO() {
    }

    public StatisticsPOJO(Integer id, GregorianCalendar date, Double percentage) {
        this.id = id;
        this.date = date;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
