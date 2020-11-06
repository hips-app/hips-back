package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "calories_data_point")
public class CaloriesDataPoint extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    UserAccount userAccount;
    Double calories;
    Date date;

    public CaloriesDataPoint() {
    }

    public CaloriesDataPoint(UserAccount userAccount, Double calories, Date date) {
        this.userAccount = userAccount;
        this.calories = calories;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Double getCalories() {
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
