package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequests.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequests.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/cars")
public class CarsController {
	// Dependencies
	private CarService carService;

	// Dependency Injection
	public CarsController(CarService carService) {
		super();
		this.carService = carService;
	}

	// lists all cars with pageSize
	@GetMapping("find-all")
	public DataResult<List<CarListDto>> findAll(@RequestParam(defaultValue = " 1") int pageNo,
			@RequestParam(defaultValue = " 100") int pageSize) {
		return this.carService.findAll(pageNo, pageSize);
	}
	@GetMapping("find-all-available")
	public DataResult<List<CarListDto>> findAllAvailable(@RequestParam(defaultValue = " 1") int pageNo,
			@RequestParam(defaultValue = " 1000") int pageSize) {
		return this.carService.findAllAvailable(pageNo, pageSize);
	}

	@GetMapping("find-all-by-brand-id")
	public DataResult<List<CarListDto>> findAllByBrandId(@RequestParam int brandId, @RequestParam int pageNo,
			@RequestParam(defaultValue = " 100") int pageSize) {
		return this.carService.findAllByBrandId(brandId, pageNo, pageSize);
	}

	@GetMapping("find-all-by-color-id")
	public DataResult<List<CarListDto>> findAllByColorId(@RequestParam int colorId, @RequestParam int pageNo,
			@RequestParam(defaultValue = " 100") int pageSize) {
		return this.carService.findAllByColorId(colorId, pageNo, pageSize);
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<CarListDto> findById(@PathVariable int id) {
		return this.carService.findCarById(id);
	}

	// adds a new car
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);
	}

	// updates a current car
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}

	// deletes a car
	@DeleteMapping("delete/{id}")
	public Result delete(@Valid @PathVariable int id) {
		return this.carService.delete(id);
	}
}
