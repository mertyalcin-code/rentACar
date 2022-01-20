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

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	// Dependencies
	private CityService cityService;

	// Dependency Injection
	@Autowired
	public CitiesController(CityService cityService) {
		super();
		this.cityService = cityService;
	}

	// lists all cities in the database
	@GetMapping("find-all")
	public DataResult<List<CityListDto>> findAll() {

		return cityService.findAll();
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<CityListDto> findById(@PathVariable int id) {
		return cityService.findById(id);
	}

	// Adds a new city
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateCityRequest createCityRequest) {
		return this.cityService.add(createCityRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) {
		return this.cityService.update(updateCityRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.cityService.delete(id);
	}

}
