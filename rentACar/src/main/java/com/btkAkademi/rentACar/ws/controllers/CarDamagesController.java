package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarDamageService;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cardamage")
public class CarDamagesController {
	private CarDamageService carDamageService;
	@Autowired
	public CarDamagesController(CarDamageService carDamageService) {
		super();
		this.carDamageService = carDamageService;
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) {
		return this.carDamageService.add(createCarDamageRequest);
	}
}
