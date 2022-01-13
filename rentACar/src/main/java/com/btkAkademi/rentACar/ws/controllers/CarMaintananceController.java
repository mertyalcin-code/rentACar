package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintananceDto;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carmaintanance")
public class CarMaintananceController {
	private CarMaintananceService carMaintananceService;
	@Autowired
	public CarMaintananceController(CarMaintananceService carMaintananceService) {
		super();
		this.carMaintananceService = carMaintananceService;
	}
	@GetMapping("getallinmaintanance")
	public DataResult<List<CarMaintananceDto>> getall() {
		return this.carMaintananceService.getAll();
	}
	@PostMapping
	public Result add(@RequestBody @Valid CreateCarMaintananceRequest createCarMaintananceRequest) {

		return this.carMaintananceService.add(createCarMaintananceRequest);
	}
}
