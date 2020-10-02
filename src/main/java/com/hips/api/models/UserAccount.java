package com.hips.api.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class UserAccount extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @OneToOne
    Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    UserType type;
    boolean autoRenewSubscription;

    public UserAccount() {
    }

    public UserAccount(Account account, UserType type, boolean autoRenewSubscription) {
        this.account = account;
        this.type = type;
        this.autoRenewSubscription = autoRenewSubscription;
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public boolean isAutoRenewSubscription() {
        return autoRenewSubscription;
    }

    public void setAutoRenewSubscription(boolean autoRenewSubscription) {
        this.autoRenewSubscription = autoRenewSubscription;
    }

    @Override
    public String toString() {
        return "userAccount{" + "id=" + id + ", account=" + account + ", type="
                + type + ", autoRenewSubscription=" + autoRenewSubscription + 
                ", isActive=" + this.isActive() + ", createdAt=" + this.getCreatedAt() +
                ", updatedAt=" + this.getUpdatedAt() + '}';
    }
}
