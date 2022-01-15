package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@RestController
@RequestMapping("/api/additionalservice")
public class AdditionalServicesController {
	private AdditionalServiceService additionalServiceService;
	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		super();
		this.additionalServiceService = additionalServiceService;
	}
	@GetMapping("getallbyrentalid/{id}")
	public Result getAllByRentalId(@PathVariable int id) {
		return additionalServiceService.findAllByRentalId(id);
		
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalService) {
		return this.additionalServiceService.add(createAdditionalService);
	}
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}
	@DeleteMapping("delete/{id}")
	public Result update(@PathVariable int id) {
		return this.additionalServiceService.delete(id);
	}
}
