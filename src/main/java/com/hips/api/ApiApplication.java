package com.hips.api;

import com.hips.api.models.Account;
import com.hips.api.models.AccountType;
import com.hips.api.models.SpecialistAccount;
import com.hips.api.models.SpecialistType;
import com.hips.api.models.SubscriptionType;
import com.hips.api.models.UserAccount;
import com.hips.api.repositories.AccountTypeRepository;
import com.hips.api.repositories.SpecialistAccountRepository;
import com.hips.api.repositories.SpecialistTypeRepository;
import com.hips.api.services.SubscriptionTypeService;
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

<<<<<<< HEAD
    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
=======
  @Autowired
  private SpecialistAccountRepository specialistAccountRepository;
>>>>>>> develop

  @Autowired
  private SpecialistTypeRepository specialistTypeRepository;

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

<<<<<<< HEAD
		AccountType accountType1 =accountTypeRepository.save(userType);
		AccountType accountType = accountTypeRepository.save(expertType);

		SpecialistType specialistType = new SpecialistType("Nutritionist");
		SpecialistType specialistType1 = new SpecialistType("Personal Trainer");

		specialistType = specialistTypeRepository.save(specialistType);
		specialistType1 = specialistTypeRepository.save(specialistType1);

		String salt = BCrypt.gensalt();
		String pass = BCrypt.hashpw("12345", salt);

		Account account = new Account(
				UUID.randomUUID().toString(),
				accountType,
				"s@test.com",
				"Sportacus",
				"Helth",
				pass,
				salt,
				""
		);

		SpecialistAccount specialistAccount = new SpecialistAccount(
				account,
				specialistType,
				"103212345",
				"Doctor in nutrition"
		);

		specialistAccountRepository.save(specialistAccount);

		Account account1 = new Account(
				UUID.randomUUID().toString(),
				accountType1,
				"prueba@test.com",
				"Pepito",
				"Perez",
				pass,
				salt,
				""
		);
 //       UserAccount userAccount= new UserAccount

		//SpecialistAccount specialistAccount1 = new SpecialistAccount(
		//		account1,
		//		specialistType1,
		//		"103212345",
		//		"Doctor in nutrition"
		//);

		//specialistAccountRepository.save(specialistAccount1);

        SubscriptionType subscriptionType = new SubscriptionType("Premium",20,"Usuario premium con acceso a rutinas de entrenamiento y dietas a seguir sugeridas por profesionales recomendados");
        subscriptionTypeService.save(subscriptionType);
	}
=======
  @EventListener
  public void appReady(ApplicationReadyEvent event) {}
>>>>>>> develop
}
