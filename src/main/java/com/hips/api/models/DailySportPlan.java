package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "daily_sport_plan")
public class DailySportPlan extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    SportPlan sportPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    WeekDay weekDay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    Calendar date;

    @Column(length = 500)
    String description;

    public DailySportPlan() {
    }

    public DailySportPlan(SportPlan sportPlan, WeekDay weekDay, String description, Calendar date) {
        this.sportPlan = sportPlan;
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

    public SportPlan getSportPlan() {
        return sportPlan;
    }

    public void setSportPlan(SportPlan sportPlan) {
        this.sportPlan = sportPlan;
    }


    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DailySportPlan{" +
                "id=" + id +
                ", sportPlan=" + sportPlan +
                ", weekDay=" + weekDay +
                ", description='" + description + '\'' +
                '}';
    }
}
