package com.hips.api.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "account_token_whitelist")
public class AccountTokenWhitelist extends Auditable
        implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    Account account;
    @Column(nullable = false, columnDefinition = "TEXT")
    String token;

    public AccountTokenWhitelist() {
    }

    public AccountTokenWhitelist(Account account, String token) {
        this.account = account;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "accountTokenWhitelist{" + "id=" + id + ", account=" + account +
                ", token=" + token + ", isActive=" + this.isActive() + ", createdAt=" +
                this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + '}';
    }
}
