package com.hips.api.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "food_plan")
public class FoodPlan extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    UserGoal userGoal;

    @Column(length = 500)
    String description;

    public FoodPlan() {
    }

    public FoodPlan(UserGoal userGoal, Date startDate, Date endDate, String description) {
        this.userGoal = userGoal;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserGoal getUserGoal() {
        return userGoal;
    }

    public void setUserGoal(UserGoal userGoal) {
        this.userGoal = userGoal;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
