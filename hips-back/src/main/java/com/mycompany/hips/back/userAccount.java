
package com.mycompany.hips.back;

import java.util.*;

public class userAccount {
    
    int id;
    int account;
    int type;
    boolean autoRenewSubscription;
    boolean isActive;
    Date createdAt;
    Date updatedAt;

    public userAccount() {
    }

    public userAccount(int id, int account, int type, boolean autoRenewSubscription,
            boolean isActive, Date createdAt, Date updatedAt) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.autoRenewSubscription = autoRenewSubscription;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAutoRenewSubscription() {
        return autoRenewSubscription;
    }

    public void setAutoRenewSubscription(boolean autoRenewSubscription) {
        this.autoRenewSubscription = autoRenewSubscription;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "userAccount{" + "id=" + id + ", account=" + account + ", type="
                + type + ", autoRenewSubscription=" + autoRenewSubscription + 
                ", isActive=" + isActive + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
