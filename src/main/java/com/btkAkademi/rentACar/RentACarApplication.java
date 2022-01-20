package com.btkAkademi.rentACar;

import java.util.Arrays;

import org.apache.catalina.filters.CorsFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
