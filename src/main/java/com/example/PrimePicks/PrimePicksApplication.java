package com.example.PrimePicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Handler;
@RestController
@SpringBootApplication
public class PrimePicksApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimePicksApplication.class, args);




	}


	@GetMapping
	@RequestMapping("/")
	public static String Hello(){
		return "Hello Spring Boot";
	}

}
