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
  Integer heightInCentimeters;
  Integer weightInKilograms;

  public ProfileResponse(
    Account account,
    UserAccount userAccount,
    UserGoal userGoal,
    UserMedicalData userMedicalData
  ) {
    this.firstName = account.getFirstName();
    this.id = account.getId();
    this.email = account.getEmail();
    this.goalDescription = userGoal != null ? userGoal.getDescription() : null;
    this.profilePicture = account.getProfilePicture();
    this.birthDay = userMedicalData != null ? userMedicalData.getBirthDay() : null;
    this.lastName = account.getLastName();
    this.goalExpirationDate =
      userGoal != null ? userGoal.getExpirationDate() : null;
    this.heightInCentimeters = userMedicalData != null ? userMedicalData.getHeightInCentimeters() : null;
    this.weightInKilograms = userMedicalData != null ? userMedicalData.getWeightInKilograms() : null;
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

  public Integer getHeightInCentimeters() {
    return heightInCentimeters;
  }

  public void setHeightInCentimeters(Integer heightInCentimeters) {
    this.heightInCentimeters = heightInCentimeters;
  }

  public Integer getWeightInKilograms() {
    return weightInKilograms;
  }

  public void setWeightInKilograms(Integer weightInKilograms) {
    this.weightInKilograms = weightInKilograms;
  }
}
