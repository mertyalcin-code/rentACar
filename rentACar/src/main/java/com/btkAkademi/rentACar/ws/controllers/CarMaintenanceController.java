package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carmaintenance")
public class CarMaintenanceController {
	private CarMaintenanceService carMaintenanceService;
	@Autowired
	public CarMaintenanceController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}
	@GetMapping("getallinmaintenance")
	public DataResult<List<CarMaintenanceDto>> getall() {
		return this.carMaintenanceService.getAll();
	}
	@PostMapping
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintananceRequest) {

		return this.carMaintenanceService.add(createCarMaintananceRequest);
	}
}
