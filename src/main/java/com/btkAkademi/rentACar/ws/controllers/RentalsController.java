package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalsController {
	// Dependencies
	private RentalService rentalService;

	// Dependency Injection
	@Autowired
	public RentalsController(RentalService rentalService) {
		super();
		this.rentalService = rentalService;
	}

	// lists all rentals in the system
	@GetMapping("get-all")
	public DataResult<List<RentalListDto>> findAll(@RequestParam int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		return rentalService.findAll(pageNo, pageSize);
	}

	@GetMapping("find-all-by-customer-id/{id}")
	public DataResult<List<RentalListDto>> findAllByCustomerId(@PathVariable int id) {
		return rentalService.findAllByCustomerId(id);
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<RentalListDto> findById(@PathVariable int id) {
		return rentalService.findById(id);
	}

	// Adds a new rental
	@PostMapping("add-for-individual-customer")
	public Result addForIndividualCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForIndividualCustomer(createRentalRequest);
	}

	@PostMapping("add-for-corporate-customer")
	public Result addForCorporateCustomer(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.addForCorporateCustomer(createRentalRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.rentalService.delete(id);
	}
}
