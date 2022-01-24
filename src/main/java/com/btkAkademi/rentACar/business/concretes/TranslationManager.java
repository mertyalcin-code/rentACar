package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.TranslationService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.TranslationSearchListDto;
import com.btkAkademi.rentACar.business.requests.translationRequests.CreateTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.DeleteTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.UpdateTranslationRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.TranslationDao;
import com.btkAkademi.rentACar.entities.concretes.Translation;

@Service
public class TranslationManager implements TranslationService {
	// Dependencies
	private TranslationDao translationDao;
	private ModelMapperService modelMapperService;

	// Dependency Injection
	@Autowired
	public TranslationManager(TranslationDao translationDao, ModelMapperService modelMapperService) {
		this.translationDao = translationDao;
		this.modelMapperService = modelMapperService;
	}

	// Finds all Translations
	@Override
	public DataResult<List<TranslationSearchListDto>> getAll() {
		List<Translation> translations = this.translationDao.findAll();
		List<TranslationSearchListDto> translationSearchListDtos = translations.stream()
				.map(translation -> modelMapperService.forDto().map(translation, TranslationSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<TranslationSearchListDto>>(translationSearchListDtos,
				Messages.TRANSLATIONSLISTED);
	}

	// Adds a new Translation
	@Override
	public Result add(CreateTranslationRequest createTranslationRequest) {
		Translation translation = modelMapperService.forRequest().map(createTranslationRequest, Translation.class);
		translationDao.save(translation);
		return new SuccessResult(Messages.TRANSLATIONADD);
	}

	// Deletes a traslation
	@Override
	public Result delete(DeleteTranslationRequest deleteTranslationRequest) {
		Translation translation = modelMapperService.forRequest().map(deleteTranslationRequest, Translation.class);
		translationDao.delete(translation);
		return new SuccessResult(Messages.TRANSLATIONDELETE);
	}

	// Updates a Translation
	@Override
	public Result update(UpdateTranslationRequest updateTranslationRequest) {
		Translation translation = modelMapperService.forRequest().map(updateTranslationRequest, Translation.class);
		translationDao.save(translation);
		return new SuccessResult(Messages.TRANSLATIONUPDATE);
	}

	// Finds a spesific translation
	@Override
	public Translation getTranslationByLanguage_IdAndWord_Id(int languageId, int wordId) {
		return this.translationDao.getTranslationByLanguage_IdAndWord_Id(languageId, wordId);
	}
}