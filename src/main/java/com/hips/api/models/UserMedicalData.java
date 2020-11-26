package com.hips.api.models;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name ="user_medical_data" )

/**
 * UserMedicalData
 */
public class UserMedicalData extends Auditable implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;
    @OneToOne(cascade = {CascadeType.ALL})
    UserAccount userAccount;
    @Column(name = "birth_day")
    Date birthDay;
    @Column(name = "height_in_centimeters",length = 100)
    int heightInCentimeters;
    @Column(name = "weight_in_kilograms",length = 100)
    int weightInKilograms;

    public UserMedicalData() {

    }

    public UserMedicalData(UserAccount owner,Date birthDay, int heightInCentimeters, int weightInKilograms) {
        this.birthDay=birthDay;
        this.userAccount=owner;
        this.weightInKilograms=weightInKilograms;
        this.heightInCentimeters=heightInCentimeters;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public UserAccount getOwner() {
        return userAccount;
    }
    public void setOwner(UserAccount owner) {
        this.userAccount = owner;
    }
    public int getWeightInKilograms() {
        return weightInKilograms;
    }
    public void setWeightInKilograms(int weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }
    public int getHeightInCentimeters() {
        return heightInCentimeters;
    }
    public void setHeightInCentimeters(int heightInCentimeters) {
        this.heightInCentimeters = heightInCentimeters;
    }
    @Override
    public String toString() {
       return "UserMedicalData{" + "id" + this.id + "owner" + this.userAccount + "birthDay"
           + this.birthDay + "heightInCentimeters" + this.heightInCentimeters
           + "weightInKilograms" + this.weightInKilograms +'}';
    }

}
