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

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomer")
public class IndividualCustomersController {
	//dependencies
	private IndividualCustomerService individualCustomerService;
	//dependency injection
	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {
		super();
		this.individualCustomerService = individualCustomerService;
	}
	//lists individual customers according to page
	@GetMapping("getAll")
	public DataResult<List<IndividualCustomerListDto>> getAll(@RequestParam int pageNo,@RequestParam(defaultValue = "10") int pageSize) {
		
		return this.individualCustomerService.getAll(pageNo,pageSize);
	}
	// adds a new individual customer
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) {

		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}
}
