package com.hips.api.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "user_subscription")
public class UserSubscription extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_subscription_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    public UserSubscription() {
    }
}
