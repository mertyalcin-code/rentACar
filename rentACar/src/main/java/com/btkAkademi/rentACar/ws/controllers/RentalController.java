package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.requests.createRentalRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rental")
public class RentalController {
private RentalService rentalService;
@Autowired
public RentalController(RentalService rentalService) {
	super();
	this.rentalService = rentalService;
}
@PostMapping("add")	
public Result add(@RequestBody @Valid createRentalRequest createRentalRequest) {
	
	return this.rentalService.add(createRentalRequest);
}
}
