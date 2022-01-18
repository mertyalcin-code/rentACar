package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.TranslationService;
import com.btkAkademi.rentACar.business.dtos.TranslationSearchListDto;
import com.btkAkademi.rentACar.business.requests.translationRequests.CreateTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.DeleteTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.UpdateTranslationRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/translations")
public class TranslationsController {

	private TranslationService translationService;

	@Autowired
	public TranslationsController(TranslationService translationService) {
		this.translationService = translationService;
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateTranslationRequest createTranslationRequest) {
		return this.translationService.add(createTranslationRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteTranslationRequest deleteTranslationRequest) {
		return this.translationService.delete(deleteTranslationRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateTranslationRequest updateTranslationRequest) {
		return this.translationService.update(updateTranslationRequest);
	}

	@GetMapping("get-all")
	public DataResult<List<TranslationSearchListDto>> getAll() {
		return this.translationService.getAll();
	}

}