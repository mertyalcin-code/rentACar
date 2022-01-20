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
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.WordDao;
import com.btkAkademi.rentACar.entities.concretes.Word;

import lombok.var;

@Service
public class WordManager implements WordService {

	private WordDao wordDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public WordManager(WordDao wordDao, ModelMapperService modelMapperService) {
		this.wordDao = wordDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<WordSearchListDto> getByKey(String key) {
		Word word = this.wordDao.getWordsByKey(key);
		WordSearchListDto wordSearchListDto = modelMapperService.forDto().map(word, WordSearchListDto.class);
		return new SuccessDataResult<WordSearchListDto>(wordSearchListDto, Messages.WORDFOUND);
	}

	@Override
	public DataResult<List<WordSearchListDto>> getAll() {
		List<Word> words = this.wordDao.findAll();
		List<WordSearchListDto> response = words.stream()
				.map(word -> modelMapperService.forDto().map(word, WordSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<WordSearchListDto>>(response, Messages.WORDSLISTED);
	}

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

	private Result existsById(int wordId) {
		var result = this.wordDao.existsById(wordId);
		if (!result) {
			return new ErrorResult(Messages.WORDNOTFOUND);
		}
		return new SuccessResult();
	}

}