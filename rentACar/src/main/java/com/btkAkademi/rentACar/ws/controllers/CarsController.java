package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cars")
public class CarsController {
	private CarService carService;

	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}
	
	@GetMapping("getall")
	public  DataResult<List<CarListDto>> getall(){
		
		return this.carService.getAll();
	}

	
@PostMapping("add")
	
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		
		return this.carService.add(createCarRequest);
	}

@PostMapping("update")

public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
	
	return this.carService.update(updateCarRequest);
}
}
