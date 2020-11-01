package com.hips.api.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subscription_type")
public class SubscriptionType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "subscription_type_id")
    private Integer id;

    @Column(name = "subscription_type_name")
    private String name;

    @OneToMany(mappedBy = "subscriptionType")
    private List<UserSubscription> userSubscriptions;

    public SubscriptionType() {

    }
    public SubscriptionType(String name) {
        this.name= name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
