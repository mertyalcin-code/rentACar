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

import com.btkAkademi.rentACar.business.abstracts.WordService;
import com.btkAkademi.rentACar.business.dtos.WordSearchListDto;
import com.btkAkademi.rentACar.business.requests.wordRequests.CreateWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.DeleteWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.UpdateWordRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
@CrossOrigin
@RestController
@RequestMapping("/api/words")
public class WordsController {
	private WordService wordService;

	@Autowired
	public WordsController(WordService wordService) {
		this.wordService = wordService;
	}
	@GetMapping("find-all")
	public DataResult<List<WordSearchListDto>> getAll() {
		return this.wordService.getAll();
	}
	@GetMapping("find-by-key/{key}")
	public DataResult<WordSearchListDto> getByKey(@PathVariable String key) {
		return this.wordService.getByKey(key);
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateWordRequest createWordRequest) {
		return this.wordService.add(createWordRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteWordRequest deleteWordRequest) {
		return this.wordService.delete(deleteWordRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateWordRequest updateWordRequest) {
		return this.wordService.update(updateWordRequest);
	}


}