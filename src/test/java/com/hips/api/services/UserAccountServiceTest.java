package com.hips.api.services;


import com.hips.api.models.UserAccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserAccountServiceTest {

    @Autowired
    private UserAccountService userAccountService;

    @Test
    void findUserAccountById (){
        UserAccount userAccount = userAccountService.findByIdAccount(3);
        Assertions.assertNotNull(userAccount);
        Assertions.assertEquals("Test", userAccount.getAccount().getFirstName());
        UserAccount userAccount1 = userAccountService.findByIdAccount(1);
        Assertions.assertNull(userAccount1);
    }

    @Test
    void userAccountHasPayment(){
        UserAccount userAccount = userAccountService.findByIdAccount(3);
        Assertions.assertEquals(false, userAccountService.hasPayment(userAccount));
    }

    @Test
    void userAccountHasAutoRenewSubscription(){
        UserAccount userAccount = userAccountService.findByIdAccount(3);
        Assertions.assertEquals(false, userAccountService.hasAutoRenewSubscription(userAccount));
    }
}
