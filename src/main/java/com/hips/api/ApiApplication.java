package com.hips.api;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Bean;

import com.hips.api.models.*;
import com.hips.api.repositories.UserAccountRepository;

@RestController
@EnableAutoConfiguration
@EnableJpaAuditing
@SpringBootApplication
public class ApiApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
