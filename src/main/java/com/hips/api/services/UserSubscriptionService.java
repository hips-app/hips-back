package com.hips.api.services;

import com.hips.api.models.UserSubscription;
import com.hips.api.repositories.UserSubscriptionRepository;

import org.springframework.stereotype.Service;

@Service

public class UserSubscriptionService {

    private UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscriptionService(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository= userSubscriptionRepository;
    }
    public void save(UserSubscription userSubscription) {
        userSubscriptionRepository.save(userSubscription);

    }

    public UserSubscription findById(Integer id){
        return userSubscriptionRepository.findById(id).orElse(null);
    }
}

