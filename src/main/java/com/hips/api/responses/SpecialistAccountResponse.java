package com.hips.api.responses;

import com.hips.api.models.Account;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.models.SpecialistType;
import com.hips.api.models.UserAccount;

import java.util.List;

public class SpecialistAccountResponse {
    //Account
    private Integer accountId;
    private String name;
    private String lastName;
    private String email;
    //SpecialistType
    private Integer specialistTypeId;
    private String specialistTypeName;
    //SpecialistAccount
    private Integer specialistAccountId;
    private String specialistAccountDrescription;

    private List<UserAccount> userAccounts;

    public SpecialistAccountResponse(Account account, SpecialistType specialistType, SpecialistAccount specialistAccount) {
        this.accountId=account.getId();
        this.name=account.getFirstName();
        this.lastName=account.getLastName();
        this.email=account.getEmail();
        this.specialistTypeId=specialistType.getId();
        this.specialistTypeName=specialistType.getName();
        this.specialistAccountId=specialistAccount.getId();
        this.specialistAccountDrescription=specialistAccount.getDescription();
    }

    public SpecialistAccountResponse(SpecialistAccount specialistAccount) {
        this.userAccounts = specialistAccount.getUserAccounts();
    }

    public Integer getAccountId() {
        return accountId;
    }
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Integer getSpecialistTypeId() {
        return specialistTypeId;
    }
    public void setSpecialistTypeId(Integer specialistTypeId) {
        this.specialistTypeId = specialistTypeId;
    }
    public String getTypeSpecialist() {
        return specialistTypeName;
    }
    public void setTypeSpecialist(String typeSpecialist) {
        this.specialistTypeName = typeSpecialist;
    }
    public Integer getSpecialistAccountId() {
        return specialistAccountId;
    }
    public void setSpecialistAccountId(Integer specialistAccountId) {
        this.specialistAccountId = specialistAccountId;
    }
    public String getDrescription() {
        return specialistAccountDrescription;
    }
    public void setDrescription(String drescription) {
        this.specialistAccountDrescription = drescription;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
