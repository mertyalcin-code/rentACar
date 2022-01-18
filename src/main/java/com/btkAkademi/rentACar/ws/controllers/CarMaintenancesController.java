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
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequests.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequests.UpdateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/car-maintenances")
public class CarMaintenancesController {
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		super();
		this.carMaintenanceService = carMaintenanceService;
	}

	@GetMapping("find-all")
	public DataResult<List<CarMaintenanceListDto>> findAll() {
		return this.carMaintenanceService.findAll();
	}

	@GetMapping("find-all-by-car-id/{id}")
	public DataResult<List<CarMaintenanceListDto>> findAllByCarId(@PathVariable int id) {
		return this.carMaintenanceService.findAllByCarId(id);
	}

	@GetMapping("findbyid/{id}")
	public DataResult<CarMaintenanceListDto> findById(@PathVariable int id) {
		return this.carMaintenanceService.findById(id);
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintananceRequest) {
		return this.carMaintenanceService.add(createCarMaintananceRequest);
	}

	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdateCarMaintananceRequest updateCarMaintananceRequest) {
		return this.carMaintenanceService.update(updateCarMaintananceRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result add(@PathVariable int id) {
		return this.carMaintenanceService.delete(id);
	}
}
