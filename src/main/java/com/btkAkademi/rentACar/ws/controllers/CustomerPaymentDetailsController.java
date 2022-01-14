package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CustomerPaymentDetailService;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.customerPaymentDetailRequest.CreateCustomerPaymentDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerPaymentDetailRequest.UpdateCustomerPamentDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customerpaymentdetails")
public class CustomerPaymentDetailsController {
	private CustomerPaymentDetailService customerPaymentDetailService;
	@Autowired
	public CustomerPaymentDetailsController(CustomerPaymentDetailService customerPaymentDetailService) {
		super();
		this.customerPaymentDetailService = customerPaymentDetailService;
	}
	@GetMapping ("findbycustomerid/{id}")
	public Result findByCustomerId(@Valid @PathVariable int id) {

		return this.customerPaymentDetailService.findCustomerPaymentDetailsByCustomerId(id);
	}
	@GetMapping ("findbyid/{id}")
	public Result get(@Valid @PathVariable int id) {

		return this.customerPaymentDetailService.findById(id);
	}
	
	
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCustomerPaymentDetailRequest createCustomerPaymentDetailRequest) {

		return this.customerPaymentDetailService.add(createCustomerPaymentDetailRequest);
	}
	@PostMapping("update")
	public Result update(@RequestBody @Valid UpdateCustomerPamentDetailsRequest updateCustomerPamentDetailsRequest) {

		return this.customerPaymentDetailService.update(updateCustomerPamentDetailsRequest);
	}
	@PostMapping("delete/{id}")
	public Result delete(@PathVariable int id) {

		return this.customerPaymentDetailService.delete(id);
	}
}
