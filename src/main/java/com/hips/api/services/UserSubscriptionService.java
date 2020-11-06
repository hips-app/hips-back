package com.hips.api.services;

import java.util.Calendar;
import java.util.Date;

import com.hips.api.models.SubscriptionType;
import com.hips.api.models.UserAccount;
import com.hips.api.models.UserSubscription;
import com.hips.api.repositories.UserSubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscriptionService(UserSubscriptionRepository userSubscriptionRepository) {
        this.userSubscriptionRepository= userSubscriptionRepository;
    }
    public void save(UserSubscription userSubscription) {
        userSubscriptionRepository.save(userSubscription);

    }
    public Date createDate(Date date) {
        Calendar newDate= Calendar.getInstance();
        newDate.setTime(date);
        newDate.add(Calendar.DAY_OF_MONTH, 30);
        date = newDate.getTime();
        return date;
    }
    public void createSubscription(UserAccount userAccount, SubscriptionType subscriptionType, Date expirationDate) {
        expirationDate = createDate(expirationDate);
        UserSubscription userSubscription = new UserSubscription(subscriptionType, userAccount, expirationDate);
        save(userSubscription);
    }
    public UserSubscription findByUserAccount(UserAccount userAccount) {
        return userSubscriptionRepository.findByUserAccount(userAccount);

    }

    public UserSubscription findById(Integer id){
        return userSubscriptionRepository.findById(id).orElse(null);
    }
}

