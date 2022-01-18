package com.btkAkademi.rentACar.ws.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.segmentRequest.createSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/segments")
public class SegmentController {
	private SegmentService segmentService;
	@Autowired
	public SegmentController(SegmentService segmentService) {
		super();
		this.segmentService = segmentService;
	}
	
	@PostMapping("add")
	public Result addForIndividualCustomer(@RequestBody @Valid createSegmentRequest createSegmentRequest) {
		return this.segmentService.add(createSegmentRequest);
	}
	
}
