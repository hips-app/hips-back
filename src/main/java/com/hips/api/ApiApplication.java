package com.hips.api;

import com.hips.api.models.Account;
import com.hips.api.models.AccountType;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.models.SpecialistType;
import com.hips.api.repositories.AccountTypeRepository;
import com.hips.api.repositories.SpecialistAccountRepository;
import com.hips.api.repositories.SpecialistTypeRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class ApiApplication {

  @Autowired
  private AccountTypeRepository accountTypeRepository;

  @Autowired
  private SpecialistAccountRepository specialistAccountRepository;

  @Autowired
  private SpecialistTypeRepository specialistTypeRepository;

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  @EventListener
  public void appReady(ApplicationReadyEvent event) {}
}
