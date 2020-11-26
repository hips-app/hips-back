package com.hips.api.services;

import java.util.List;

import com.hips.api.models.SubscriptionType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubscriptionTypeServiceTest {
    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    /*@Test
    void findAll(){
        List<SubscriptionType> response = subscriptionTypeService.findAll();
        Assertions.assertEquals(1,response.size());
    }
    @Test
    void findSubscriptionTypeById(){
        SubscriptionType response = subscriptionTypeService.findById(1);
        Assertions.assertEquals("Premium",response.getName());
    }
    @Test
    void findSpecialistAccountByWrongId(){
        Assertions.assertNull(subscriptionTypeService.findById(2));
    }*/
}
