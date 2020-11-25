package com.hips.api.services;

import com.hips.api.models.UserSubscription;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class UserSubscriptionServiceTests {

    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @Test
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
    void findById() {
        UserSubscription response = userSubscriptionService.findById(1);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.getId());
    }

}
