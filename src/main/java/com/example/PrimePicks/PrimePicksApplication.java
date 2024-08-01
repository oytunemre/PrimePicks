package com.example.PrimePicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@ComponentScan("com.example.PrimePicks.*")
@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class PrimePicksApplication {

	public static void main(String[] args) {
		ApplicationContext a =SpringApplication.run(PrimePicksApplication.class, args);


		System.out.println("Program started successfully");

	}




	}






