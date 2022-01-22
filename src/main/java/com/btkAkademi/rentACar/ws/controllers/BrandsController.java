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
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/brands")
public class BrandsController {
	// Dependencies
	private BrandService brandService;

	// Dependency Injection
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	// lists all brands
	@GetMapping("find-all")
	public DataResult<List<BrandListDto>> findAll() {
		return this.brandService.findAll();
	}

	// finds by id
	@GetMapping("find-by-id/{id}")
	public Result findById(@PathVariable int id) {
		return this.brandService.findById(id);
	}

	// adds a new brand
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {

		return this.brandService.add(createBrandRequest);
	}

	// updates a current brand
	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {

		return this.brandService.update(updateBrandRequest);
	}

	// deletes a brand
	@DeleteMapping("delete/{id}")
	public Result delete(@Valid @PathVariable int id) {
		return this.brandService.delete(id);
	}

}
