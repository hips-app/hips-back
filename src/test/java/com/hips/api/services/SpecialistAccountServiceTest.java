package com.hips.api.services;

import java.util.List;

import com.hips.api.models.SpecialistAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpecialistAccountServiceTest {
    @Autowired
    private SpecialistAccountService specialistAccountService;

    @Test
    void findAll(){
        List<SpecialistAccount> specialists = specialistAccountService.findAll();
        Assertions.assertEquals(2, specialists.size());
    }

    @Test
    void findSpecialistAccountById(){
        SpecialistAccount response = specialistAccountService.findById(1);
        Assertions.assertEquals("Doctor in nutrition",response.getDescription());
    }
    @Test
    void findSpecialistAccountByWrongId(){
        Assertions.assertNull(specialistAccountService.findById(4));
    }
}
