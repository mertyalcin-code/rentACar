package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequest.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequest.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

	private BrandService brandService;

	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}
	
	@GetMapping("getall")
	public DataResult<List<BrandListDto>> getall(){
		return this.brandService.getAll();
	}
	
	@PostMapping("add")
	
	public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) {
		
		return this.brandService.add(createBrandRequest);
	}
@PostMapping("update")
	
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) {
		
		return this.brandService.update(updateBrandRequest);
	}



}
