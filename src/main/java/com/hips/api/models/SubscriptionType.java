package com.hips.api.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subscription_type")
public class SubscriptionType extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subscription_type_id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "subscription_type_name")
    private String name;

    @OneToMany(mappedBy = "subscriptionType")
    private List<UserSubscription> userSubscriptions;

    @Column(name = "subscription_type_price")
    private int price;

    @Column(name = "subscription_type_description")
    private String description;

    public SubscriptionType() {

    }
    public SubscriptionType(String name, int price, String description ) {
        this.description=description;
        this.price=price;
        this.name= name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
