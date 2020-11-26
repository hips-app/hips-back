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
  String errorMessage;
  Integer accountType;
  public LogInResponse() {
      //this method is empty
  }

  public LogInResponse(String errorMessagee) {
    this.errorMessage = errorMessagee;
  }

  public LogInResponse(Account account, String token) {
    this.id = account.getId();
    this.subscription= null;
    this.lastName = account.getLastName();
    this.email = account.getEmail();
    this.firstName = account.getFirstName();
    this.accountType = account.getType().getId();
    this.token = token;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getAccountType() {
    return accountType;
  }

  public void setAccountType(Integer accountType) {
    this.accountType = accountType;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }
  public Integer getId() {
    return id;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public String getErrorMesage() {
    return errorMessage;
  }

  public void setErrorMesage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public UserSubscription getSubscription() {
    return subscription;
  }

  public void setSubscription(UserSubscription subscription) {
    this.subscription = subscription;
  }
}
