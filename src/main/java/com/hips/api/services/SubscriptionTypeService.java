package com.hips.api.services;

import java.util.ArrayList;
import java.util.List;

import com.hips.api.models.SubscriptionType;
import com.hips.api.repositories.SubscriptionTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionTypeService {
    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository=subscriptionTypeRepository;
    }
    public void save(SubscriptionType subscriptionType) {
        subscriptionTypeRepository.save(subscriptionType);
    }

    public List<SubscriptionType> findAll() {
        Iterable<SubscriptionType> it = subscriptionTypeRepository.findAll();
        List<SubscriptionType> subscriptionTypes = new ArrayList<SubscriptionType>();
        it.forEach(e -> subscriptionTypes.add(e));
        return subscriptionTypes;
    }
    public SubscriptionType findById(Integer id){
        return subscriptionTypeRepository.findById(id).orElse(null);
    }
}
