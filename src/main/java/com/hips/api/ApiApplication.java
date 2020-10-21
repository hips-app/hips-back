package com.hips.api;

import com.hips.api.models.AccountType;

import com.hips.api.repositories.AccountTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class ApiApplication {

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@EventListener
	public void appReady(ApplicationReadyEvent event){

		AccountType userType = new AccountType("User");
		AccountType expertType = new AccountType("Expert");

		accountTypeRepository.save(userType);
		accountTypeRepository.save(expertType);
	}
}

