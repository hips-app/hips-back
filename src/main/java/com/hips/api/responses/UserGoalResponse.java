package com.hips.api.responses;

import com.hips.api.models.UserAccount;
import com.hips.api.models.UserGoal;

import java.io.Serializable;
import java.util.Date;

public class UserGoalResponse  implements Serializable {

    int id;
    UserAccount userAccount;
    String description;
    Date expirationDate;

    public UserGoalResponse(UserGoal userGoal) {
        this.id = userGoal.getId();
        this.expirationDate = userGoal.getExpirationDate();
        this.description = userGoal.getDescription();
        this.userAccount = userGoal.getUserAccount();
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
