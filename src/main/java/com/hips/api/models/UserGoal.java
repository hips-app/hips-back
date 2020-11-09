package com.hips.api.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_goal")
public class UserGoal extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    UserAccount userAccount;

    @Column(length = 500)
    String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @CreatedDate
    Date expirationDate;

    public UserGoal() {
    }

    public UserGoal( UserAccount userAccount, String description, Date expirationDate) {
        this.expirationDate = expirationDate;
        this.description = description;
        this.userAccount = userAccount;
    }


    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "UserGoal{" +
                "id=" + id +
                ", userAccount=" + userAccount +
                ", description='" + description + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
