package com.hips.api.responses;

import com.hips.api.models.Account;
import com.hips.api.models.UserAccount;
import com.hips.api.models.UserGoal;
import com.hips.api.models.UserMedicalData;
import java.io.Serializable;
import java.util.Date;

public class ProfileResponse implements Serializable {

  Integer id;
  String firstName;
  String lastName;
  String email;
  String profilePicture;
  String goalDescription;
  Date goalExpirationDate;
  Date birthDay;
  int heightInCentimeters;
  int weightInKilograms;

  public ProfileResponse(
    Account account,
    UserAccount userAccount,
    UserGoal userGoal,
    UserMedicalData userMedicalData
  ) {
    this.firstName = account.getFirstName();
    this.id = account.getId();
    this.email = account.getEmail();
    this.goalDescription = userGoal.getDescription();
    this.profilePicture = account.getProfilePicture();
    this.birthDay = userMedicalData.getBirthDay();
    this.lastName = account.getLastName();
    this.goalExpirationDate = userGoal.getExpirationDate();
    this.heightInCentimeters = userMedicalData.getHeightInCentimeters();
    this.weightInKilograms = userMedicalData.getWeightInKilograms();
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getId() {
    return id;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getGoalExpirationDate() {
    return goalExpirationDate;
  }

  public void setGoalExpirationDate(Date goalExpirationDate) {
    this.goalExpirationDate = goalExpirationDate;
  }

  public String getGoalDescription() {
    return goalDescription;
  }

  public void setGoalDescription(String goalDescription) {
    this.goalDescription = goalDescription;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }

  public int getHeightInCentimeters() {
    return heightInCentimeters;
  }

  public void setHeightInCentimeters(int heightInCentimeters) {
    this.heightInCentimeters = heightInCentimeters;
  }

  public int getWeightInKilograms() {
    return weightInKilograms;
  }

  public void setWeightInKilograms(int weightInKilograms) {
    this.weightInKilograms = weightInKilograms;
  }
}
