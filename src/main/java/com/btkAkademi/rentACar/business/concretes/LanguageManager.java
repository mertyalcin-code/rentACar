package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.LanguageService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.LanguageSearchListDto;
import com.btkAkademi.rentACar.business.requests.languageRequests.CreateLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.DeleteLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.UpdateLanguageRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.LanguageDao;
import com.btkAkademi.rentACar.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService {
	// Dependencies
	private LanguageDao languageDao;
	private ModelMapperService modelMapperService;

	// Dependency Injection
	@Autowired
	public LanguageManager(LanguageDao languageDao, ModelMapperService modelMapperService) {
		this.languageDao = languageDao;
		this.modelMapperService = modelMapperService;
	}

	// Finds All Languages
	@Override
	public DataResult<List<LanguageSearchListDto>> getAll() {
		List<Language> languages = this.languageDao.findAll();
		List<LanguageSearchListDto> languageSearchListDtos = languages.stream()
				.map(language -> modelMapperService.forDto().map(language, LanguageSearchListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<LanguageSearchListDto>>(languageSearchListDtos, Messages.LANGUAGELIST);
	}

	// Adds a new Language
	@Override
	public Result add(CreateLanguageRequest createLanguageRequest) {
		Language language = modelMapperService.forRequest().map(createLanguageRequest, Language.class);
		languageDao.save(language);
		return new SuccessResult(Messages.LANGUAGEADD);
	}

	// Deletes a language if there is no relation in the database
	@Override
	public Result delete(DeleteLanguageRequest deleteLanguageRequest) {
		Language language = modelMapperService.forRequest().map(deleteLanguageRequest, Language.class);
		languageDao.delete(language);
		return new SuccessResult(Messages.LANGUAGEDELETE);
	}

	// Deletes a language if there is no relation in the database
	@Override
	public Result update(UpdateLanguageRequest updateLanguageRequests) {
		Language language = modelMapperService.forRequest().map(updateLanguageRequests, Language.class);
		languageDao.save(language);
		return new SuccessResult(Messages.LANGUAGEUPDATE);
	}

	// finds language by name
	@Override
	public DataResult<LanguageSearchListDto> getByLanguageName(String languageName) {
		Language language = this.languageDao.getLanguagesByName(languageName);
		// var result=this.languageDao.getById(id);
		LanguageSearchListDto languageSearchListDto = modelMapperService.forDto().map(language,
				LanguageSearchListDto.class);
		return new SuccessDataResult<LanguageSearchListDto>(languageSearchListDto, Messages.LANGUAGELIST);
	}
}