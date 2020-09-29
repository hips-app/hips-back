
package com.hips.models;

import java.util.Date;

public class accountTokenWhithelist {
    
    int id;
    int account;
    String token;
    boolean isActive;
    Date createdAt;
    Date updatedAt;

    public accountTokenWhithelist() {
    }

    public accountTokenWhithelist(int id, int account, String token, boolean isActive, Date createdAt, Date updatedAt) {
        this.id = id;
        this.account = account;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
        return "accountTokenWhithelist{" + "id=" + id + ", account=" + account +
                ", token=" + token + ", isActive=" + isActive + ", createdAt=" +
                createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
