package com.hips.api.services;

import com.hips.api.models.UserAccount;
import com.hips.api.models.UserSubscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class UserSubscriptionServiceTests {

    @Autowired
    private UserSubscriptionService userSubscriptionService;
    @Autowired
    private UserAccountService userAccountService;

    /*@Test
    void createDate_DatesDiffInDaysMustBe30() {
        Date now = new Date();
        Date response = userSubscriptionService.createDate(now);
        System.out.println(response.toString());
        Assertions.assertNotNull(response);;
        long diffInMilliseconds = Math.abs(response.getTime() - now.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        Assertions.assertEquals(30, diff);
    }

    @Test
    void findUserSubscriptionById() {
        UserSubscription response = userSubscriptionService.findById(1);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
    }
    @Test
    void findUserSubscriptionByWrongId() {
        UserSubscription response = userSubscriptionService.findById(2);
        Assertions.assertNull(response);
    }
    @Test
    void findUserSubscriptionByUserAccount(){
        UserAccount userAccount = userAccountService.findByIdAccount(3);
        Assertions.assertNotNull(userSubscriptionService.findByUserAccount(userAccount));
    }
    @Test
    void findUserSubscriptionByWrongUserAccount(){
        UserAccount userAccount = userAccountService.findByIdAccount(2);
        Assertions.assertNull(userSubscriptionService.findByUserAccount(userAccount));
    }
    @Test
    void checkSubscription(){
        UserSubscription response = userSubscriptionService.findById(1);
        UserAccount userAccount = response.getUserAccount();
        Assertions.assertEquals(true,userSubscriptionService.checkSubscription(userAccount, new Date()));
    }*/

}
