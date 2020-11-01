package com.hips.api.services;

import com.hips.api.models.SubscriptionType;
import com.hips.api.repositories.SubscriptionTypeRepository;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionTypeService {
    private SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository=subscriptionTypeRepository;
    }
    public void save(SubscriptionType subscriptionType) {
        subscriptionTypeRepository.save(subscriptionType);
    }

    public SubscriptionType findById(Integer id){
        return subscriptionTypeRepository.findById(id).orElse(null);
    }
}
