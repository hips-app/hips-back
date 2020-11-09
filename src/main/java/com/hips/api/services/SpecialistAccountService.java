package com.hips.api.services;

import java.util.ArrayList;
import java.util.List;

import com.hips.api.assistants.AuthenticationAssistant;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.repositories.SpecialistAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class SpecialistAccountService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Autowired
    private SpecialistAccountRepository specialistAccountRepository;

    public List<SpecialistAccount> findAll() {
        Iterable<SpecialistAccount> it = specialistAccountRepository.findAll();
        List<SpecialistAccount> specialistAccount = new ArrayList<SpecialistAccount>();
        it.forEach(e -> specialistAccount.add(e));
        return specialistAccount;
    }

    public  Integer getId(String token){
        Integer specialistId;
        specialistId = Integer.parseInt(AuthenticationAssistant.getJWT_Subject(jwtSecret, token));

        return specialistId;
    }

    public  SpecialistAccount findById(Integer id) {
        return specialistAccountRepository.findById(id).orElse(null);
    }

}
