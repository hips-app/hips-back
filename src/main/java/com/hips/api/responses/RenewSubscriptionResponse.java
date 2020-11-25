package com.hips.api.responses;

import java.util.Date;

import com.hips.api.models.SpecialistAccount;
import com.hips.api.models.UserSubscription;

public class RenewSubscriptionResponse {
    private Date expirationDate;
    private String typeSubscription;
    private String nameProfesional;
    private String lastNameProfesional;
    private String descriptionProfesional;

    public RenewSubscriptionResponse(UserSubscription userSubscription) {
        SpecialistAccount specialistAccount = userSubscription.getUserAccount().getSpecialistAccount();
        this.expirationDate = userSubscription.getExpirationDate();
        this.typeSubscription = userSubscription.getSubscriptionType().getDescription();
        this.nameProfesional = specialistAccount.getAccount().getFirstName();
        this.lastNameProfesional = specialistAccount.getAccount().getLastName();
        this.descriptionProfesional = specialistAccount.getDescription();
    }

    public String getNameProfesional() {
        return nameProfesional;
    }
    public void setNameProfesional(String nameProfesional) {
        this.nameProfesional = nameProfesional;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getTypeSubscription() {
        return typeSubscription;
    }
    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }
    public String getLastNameProfesional() {
        return lastNameProfesional;
    }
    public void setLastNameProfesional(String lastNameProfesional) {
        this.lastNameProfesional = lastNameProfesional;
    }
    public String getDescriptionProfesional() {
        return descriptionProfesional;
    }
    public void setDescriptionProfesional(String descriptionProfesional) {
        this.descriptionProfesional = descriptionProfesional;
    }
}
