package com.hips.api.responses;

import com.hips.api.models.SubscriptionType;

public class SubscriptionTypeResponse {
    private Integer id;
    private String name;
    private int price;
    private String description;
    public SubscriptionTypeResponse(SubscriptionType subscriptionType) {
        this.id= subscriptionType.getId();
        this.name= subscriptionType.getName();
        this.price= subscriptionType.getPrice();
        this.description= subscriptionType.getDescription();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}

