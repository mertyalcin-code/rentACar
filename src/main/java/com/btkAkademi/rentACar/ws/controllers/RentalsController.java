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
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
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
		return rentalService.findAll(pageNo, pageSize);
	}	
	@GetMapping("findallbycustomerid/{id}")
	public DataResult<List<RentalListDto>> getAllByCustomerId(@PathVariable int id){
		return rentalService.findAllByCustomerId(id);
	}	
	@GetMapping("findbyid/{id}")
	public DataResult<RentalListDto> findById(@PathVariable int id){
		return rentalService.findById(id);
	}	

	//Adds a new rental
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);
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
