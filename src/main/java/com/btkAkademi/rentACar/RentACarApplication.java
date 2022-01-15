package com.btkAkademi.rentACar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentACarApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to Mert Yalçın's Project");
		SpringApplication.run(RentACarApplication.class, args);
	}

	// To create an instance of Model Mapper
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
