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

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/promo-codes")
public class PromoCodeController {
	private PromoCodeService promoCodeService;

	@Autowired
	public PromoCodeController(PromoCodeService promoCodeService) {
		super();
		this.promoCodeService = promoCodeService;
	}

	@GetMapping("find-all")
	public DataResult<List<PromoCodeDto>> findAll() {
		return this.promoCodeService.findAll();
	} 
	@GetMapping("find-by-code/{code}")
	public DataResult<PromoCodeDto> findByCode(@PathVariable String code) {
		return this.promoCodeService.findByCode(code);
	} 
	@GetMapping("find-by-id/{id}")
	public DataResult<PromoCodeDto> findByCode(@PathVariable int id) {
		return this.promoCodeService.findById(id);
	} 

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePromoCodeRequest createPromoCodeRequest) {

		return this.promoCodeService.add(createPromoCodeRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdatePromoCodeRequest updatePromoCodeRequest) {
		return this.promoCodeService.update(updatePromoCodeRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.promoCodeService.delete(id);
	}

}
