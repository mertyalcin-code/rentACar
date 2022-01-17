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

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individual-customers")
public class IndividualCustomersController {
	// dependencies
	private IndividualCustomerService individualCustomerService;

	// dependency injection
	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}

	// lists individual customers according to page
	@GetMapping("find-all")
	public DataResult<List<IndividualCustomerListDto>> findAll(@RequestParam int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {

		return this.individualCustomerService.findAll(pageNo, pageSize);
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<IndividualCustomerListDto> findById(@PathVariable int id) {

		return this.individualCustomerService.findById(id);
	}

	// adds a new individual customer
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {

		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {

		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {

		return this.individualCustomerService.delete(id);
	}
}
