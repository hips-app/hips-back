package com.hips.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "specialist_account")
public class SpecialistAccount extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "specialist_account_id", updatable = false, nullable = false)
    int id;
    @OneToOne(cascade = {CascadeType.ALL})
    Account account;
    @ManyToOne()
    @JoinColumn(name = "specialist_type_id")
    SpecialistType type;
    @Column(length = 100)
    String documentNumber;
    @Column(columnDefinition = "TEXT")
    String description;

    @OneToMany(mappedBy = "specialistAccount")
    private List<UserAccount> userAccounts;

    public SpecialistAccount() {
    }

    public SpecialistAccount(Account account, SpecialistType type, String documentNumber, String description) {
        this.account = account;
        this.type = type;
        this.documentNumber = documentNumber;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public SpecialistType getType() {
        return type;
    }

    public void setType(SpecialistType type) {
        this.type = type;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
