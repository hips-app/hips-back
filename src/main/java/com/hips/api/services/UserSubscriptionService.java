package com.hips.api.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hips.api.models.SubscriptionType;
import com.hips.api.models.UserAccount;
import com.hips.api.models.UserSubscription;
import com.hips.api.repositories.UserAccountRepository;
import com.hips.api.repositories.UserSubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;

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
        return newDate.getTime();
    }
    public void createSubscription(UserAccount userAccount, SubscriptionType subscriptionType, Date initDate) {
        initDate = createDate(initDate);
        UserSubscription userSubscription = new UserSubscription(subscriptionType, userAccount, initDate);
        save(userSubscription);
    }
    public UserSubscription findByUserAccount(UserAccount userAccount) {
        return userSubscriptionRepository.findByUserAccount(userAccount);
    }
    public boolean checkSubscription(UserAccount userAccount, Date datenow){
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAllByUserAccount(userAccount);
        if(userSubscriptions.get(userSubscriptions.size()-1).getExpirationDate().compareTo(datenow) < 0){
            return false;
        }
        return true;
    }
    public void renewSubscription(UserAccount userAccount , Date datenow) {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAllByUserAccount(userAccount);
        boolean checkSubscription = checkSubscription(userAccount, datenow);
        if(!checkSubscription){
            SubscriptionType subscriptionType = userSubscriptions.get(userSubscriptions.size()-1).getSubscriptionType();
            Date initDate = userSubscriptions.get(userSubscriptions.size()-1).getExpirationDate();
            createSubscription(userAccount, subscriptionType, initDate);
        }
    }

    public UserSubscription findById(Integer id){
        return userSubscriptionRepository.findById(id).orElse(null);
    }
}

