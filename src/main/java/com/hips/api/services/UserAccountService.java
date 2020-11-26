package com.hips.api.services;

import com.hips.api.models.Account;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository= userAccountRepository;
    }

    public UserAccount findByAccount(Account account) {
        return userAccountRepository.findByAccount(account);
    }
    public Boolean hasPayment(UserAccount userAccount) {
        return userAccount.isPaymentMethod();
    }
    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public UserAccount findByIdAccount (int id){
        return userAccountRepository.getByAccountId(id);
    }
}
