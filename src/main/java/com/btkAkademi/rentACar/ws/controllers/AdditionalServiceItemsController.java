package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additional-service-items")
public class AdditionalServiceItemsController {
	private AdditionalServiceItemService additionalServiceItemService;
	@Autowired
	public AdditionalServiceItemsController(AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.additionalServiceItemService = additionalServiceItemService;
	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.add(createAdditionalServiceItemRequest);
	}
	
	
}
