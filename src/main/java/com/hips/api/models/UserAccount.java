package com.hips.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_id", updatable = false, nullable = false)
    int id;
    @OneToOne(cascade = {CascadeType.ALL})
    Account account;

    @OneToMany(mappedBy = "userAccount")
    private List<UserSubscription> userSubscriptions;

    @ManyToOne
    @JoinColumn(name = "specialist_account_id")
    SpecialistAccount specialistAccount;

    boolean autoRenewSubscription = false;

    private boolean paymentMethod = false;

    public UserAccount() {
    }

    public UserAccount(Account account) {
        this.account = account;
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

    public boolean isAutoRenewSubscription() {
        return autoRenewSubscription;
    }

    public void setAutoRenewSubscription(boolean autoRenewSubscription) {
        this.autoRenewSubscription = autoRenewSubscription;
    }
    public boolean isPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(boolean paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public SpecialistAccount getSpecialistAccount() {
        return specialistAccount;
    }
    public void setSpecialistAccount(SpecialistAccount specialistAccount) {
        this.specialistAccount = specialistAccount;
    }

    @Override
    public String toString() {
        return "userAccount{" + "id=" + id + ", account=" + account +
                 ", autoRenewSubscription=" + autoRenewSubscription +
                ", isActive=" + this.isActive() + ", createdAt=" + this.getCreatedAt() +
                ", updatedAt=" + this.getUpdatedAt() + '}';
    }
}
