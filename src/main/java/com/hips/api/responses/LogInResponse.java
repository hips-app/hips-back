package com.hips.api.responses;

import com.hips.api.models.Account;
import com.hips.api.models.UserSubscription;

import java.io.Serializable;

public class LogInResponse implements Serializable {

    Integer id;
    String firstName;
    String lastName;
    String email;
    String token;
    UserSubscription subscription;

    public LogInResponse() {
    }

    public LogInResponse(Account account, String token) {
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.token = token;
        this.subscription = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserSubscription getSubscription() {
        return subscription;
    }

    public void setSubscription(UserSubscription subscription) {
        this.subscription = subscription;
    }
}
