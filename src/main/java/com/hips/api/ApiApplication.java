package com.hips.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;

import com.hips.api.models.*;
//import com.hips.api.repositories.UserAccountRepository;


@SpringBootApplication
public class ApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
