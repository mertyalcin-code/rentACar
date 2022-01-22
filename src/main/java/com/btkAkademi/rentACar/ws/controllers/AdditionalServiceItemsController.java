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

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/additional-service-items")
public class AdditionalServiceItemsController {
	private AdditionalServiceItemService additionalServiceItemService;

	@Autowired
	public AdditionalServiceItemsController(AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.additionalServiceItemService = additionalServiceItemService;
	}


	@GetMapping("find-all")
	public DataResult<List<AdditionalServiceItemListDto>> findAllByRentalId() {
		return additionalServiceItemService.findAll();

	}

	@GetMapping("find-by-id/{id}")
	public DataResult<AdditionalServiceItemListDto> findById(@PathVariable int id) {
		return additionalServiceItemService.findById(id);

	}
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.add(createAdditionalServiceItemRequest);
	}
	@PutMapping("update")
	public Result add(@RequestBody @Valid UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.update(updateAdditionalServiceItemRequest);
	}
	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.additionalServiceItemService.delete(id);
	}

}
