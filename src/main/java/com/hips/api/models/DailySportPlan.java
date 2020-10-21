package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "daily_sport_plan")
public class DailySportPlan extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    SportPlan sportPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    WeekDay weekDay;

    @Column(length = 500)
    String description;

    public DailySportPlan() {
        //constructor vacio
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

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
