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
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @OneToOne(cascade = {CascadeType.ALL})
   // @JoinColumn(name = "id_user_account")
    UserAccount owner;
    @Column(name = "birth_day")
    Date birthDay;
    @Column(name = "height_in_centimeters",length = 100)
    int heightInCentimeters;
    @Column(name = "weight_in_kilograms",length = 100)
    int weightInKilograms;

    public UserMedicalData() {

    }

    public UserMedicalData(UserAccount owner,Date birthDay, int heightInCentimeters, int weightInKilograms) {
        this.owner=owner;
        this.birthDay=birthDay;
        this.heightInCentimeters=heightInCentimeters;
        this.weightInKilograms=weightInKilograms;
    }
    public UserAccount getOwner() {
        return owner;
    }
    public void setOwner(UserAccount owner) {
        this.owner = owner;
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
    @Override
    public String toString() {
       return "UserMedicalData{" + "id" + this.id + "owner" + this.owner + "birthDay"
           + this.birthDay + "heightInCentimeters" + this.heightInCentimeters
           + "weightInKilograms" + this.weightInKilograms +'}';
    }

}
