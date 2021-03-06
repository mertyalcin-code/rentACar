package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.WordService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.WordSearchListDto;
import com.btkAkademi.rentACar.business.requests.wordRequests.CreateWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.DeleteWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.UpdateWordRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.WordDao;
import com.btkAkademi.rentACar.entities.concretes.Word;

import lombok.var;

@Service
public class WordManager implements WordService {
	// Dependecies
	private WordDao wordDao;
	private ModelMapperService modelMapperService;

	// Dependecy Injection
	@Autowired
	public WordManager(WordDao wordDao, ModelMapperService modelMapperService) {
		this.wordDao = wordDao;
		this.modelMapperService = modelMapperService;
	}

	// Finds a word by key
	@Override
	public DataResult<WordSearchListDto> getByKey(String key) {
		Word word = this.wordDao.getWordsByKey(key);
		if (word == null) {
			return new ErrorDataResult<WordSearchListDto>();
		}
		WordSearchListDto wordSearchListDto = modelMapperService.forDto().map(word, WordSearchListDto.class);
		return new SuccessDataResult<WordSearchListDto>(wordSearchListDto, Messages.WORDFOUND);
	}

	// Gets all words
	@Override
	public DataResult<List<WordSearchListDto>> getAll() {
		List<Word> words = this.wordDao.findAll();
		List<WordSearchListDto> response = words.stream()
				.map(word -> modelMapperService.forDto().map(word, WordSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<WordSearchListDto>>(response, Messages.WORDSLISTED);
	}

	// Adds a new Word
	@Override
	public Result add(CreateWordRequest createWordRequest) {
		var result = BusinessRules.run(isWordExists(createWordRequest.getKey()));
		if (result != null) {
			return result;
		}
		Word word = modelMapperService.forRequest().map(createWordRequest, Word.class);
		this.wordDao.save(word);
		return new SuccessResult(Messages.WORDADD);
	}

	// deletes a word if there is no relation
	@Override
	public Result delete(DeleteWordRequest deleteWordRequest) {
		var result = BusinessRules.run(existsById(deleteWordRequest.getId()));
		if (result != null) {
			return result;
		}
		Word word = modelMapperService.forRequest().map(deleteWordRequest, Word.class);
		this.wordDao.delete(word);
		return new SuccessResult(Messages.WORDDELETED);
	}

	// updates a word
	@Override
	public Result update(UpdateWordRequest updateWordRequest) {
		var result = BusinessRules.run(existsById(updateWordRequest.getId()), isWordExists(updateWordRequest.getKey()));
		if (result != null) {
			return result;
		}
		Word word = modelMapperService.forRequest().map(updateWordRequest, Word.class);
		this.wordDao.save(word);
		return new SuccessResult(Messages.WORDUPDATED);
	}

	private Result isWordExists(String messageKey) {
		var result = this.wordDao.existsByKey(messageKey);
		if (result) {
			return new ErrorResult(Messages.WORDALREADYEXISTS);
		}
		return new SuccessResult();
	}

	// checks a word exists or not
	private Result existsById(int wordId) {
		var result = this.wordDao.existsById(wordId);
		if (!result) {
			return new ErrorResult(Messages.WORDNOTFOUND);
		}
		return new SuccessResult();
	}

}