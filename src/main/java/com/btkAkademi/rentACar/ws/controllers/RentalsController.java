package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequest.createRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@RestController
@RequestMapping("/api/rental")
public class RentalsController {
	//Dependencies
	private RentalService rentalService;
	//Dependency Injection
	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}
	//lists all rentals in the system
	@GetMapping("getAll")
	public DataResult<List<RentalListDto>> getAll(@RequestParam int pageNo, @RequestParam(defaultValue = "10") int pageSize){
		return rentalService.getAll(pageNo, pageSize);
	}	
	@GetMapping("getbyid")
	public DataResult<Rental> getAll(@RequestParam int id){
		return rentalService.findById(id);
	}	
	//Adds a new rental
	@PostMapping("add")
	public Result add(@RequestBody @Valid createRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
	}
}
