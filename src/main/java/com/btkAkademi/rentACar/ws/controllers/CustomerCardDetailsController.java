package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.dtos.CustomerCardDetailListDto;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.CreateCustomerCardDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.UpdateCustomerCardDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/customer-card-details")
public class CustomerCardDetailsController {
	private CustomerCardDetailService customerCardDetailService;

	@Autowired
	public CustomerCardDetailsController(CustomerCardDetailService customerCardDetailService) {
		super();
		this.customerCardDetailService = customerCardDetailService;
	}

	@GetMapping("find-by-customer-id/{id}")
	public DataResult<List<CustomerCardDetailListDto>> findByCustomerId(@PathVariable int id) {

		return this.customerCardDetailService.findCustomerCardDetailsByCustomerId(id);
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<CustomerCardDetailListDto> findById(@PathVariable int id) {

		return this.customerCardDetailService.findById(id);
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCustomerCardDetailRequest createCustomerCardDetailRequest) {
		System.out.println(createCustomerCardDetailRequest.getCardNo());
		return this.customerCardDetailService.add(createCustomerCardDetailRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCustomerCardDetailsRequest updateCustomerPamentDetailsRequest) {

		return this.customerCardDetailService.update(updateCustomerPamentDetailsRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {

		return this.customerCardDetailService.delete(id);
	}
}
