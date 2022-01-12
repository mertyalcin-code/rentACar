package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.brandRequest.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequest.UpdateBrandRequest;
import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequest.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/colors")
public class ColorsContoller {

	private ColorService colorService;


	
	public ColorsContoller(ColorService colorService) {
		super();
		this.colorService = colorService;
	}



	@GetMapping("getall")
	public  DataResult<List<ColorListDto>> getall(){
		return this.colorService.getAll();
	}
	
@PostMapping("add")
	
	public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) {
		
		return this.colorService.add(createColorRequest);
	}
	
@PostMapping("update")
	
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
		
		return this.colorService.update(updateColorRequest);
	}
	

}
