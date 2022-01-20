package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

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

import com.btkAkademi.rentACar.business.abstracts.LanguageService;
import com.btkAkademi.rentACar.business.dtos.LanguageSearchListDto;
import com.btkAkademi.rentACar.business.requests.languageRequests.CreateLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.DeleteLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.UpdateLanguageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

import lombok.var;
@CrossOrigin
@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
	private LanguageService languageService;

	@Autowired
	public LanguagesController(LanguageService languageService) {
		this.languageService = languageService;
	}

	@GetMapping("find-all")
	public DataResult<List<LanguageSearchListDto>> getAll() {
		return this.languageService.getAll();
	}

	@GetMapping("find-by-language-name/{languageName}")
	public DataResult<LanguageSearchListDto> getByLanguageName(@PathVariable String languageName) {
		var result = this.languageService.getByLanguageName(languageName);
		return result;
	}

	@PostMapping("add")
	public Result add(@RequestBody CreateLanguageRequest createLanguageRequest) {
		return this.languageService.add(createLanguageRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody DeleteLanguageRequest deleteLanguageRequest) {
		return this.languageService.delete(deleteLanguageRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody UpdateLanguageRequest updateLanguageRequest) {
		return this.languageService.update(updateLanguageRequest);
	}
}