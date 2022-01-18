package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.LanguageSearchListDto;
import com.btkAkademi.rentACar.business.requests.languageRequests.CreateLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.DeleteLanguageRequest;
import com.btkAkademi.rentACar.business.requests.languageRequests.UpdateLanguageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface LanguageService {

	DataResult<List<LanguageSearchListDto>> getAll();

	Result add(CreateLanguageRequest createLanguageRequest);

	Result delete(DeleteLanguageRequest deleteLanguageRequest);

	Result update(UpdateLanguageRequest updateLanguageRequests);

	DataResult<LanguageSearchListDto> getByLanguageName(String languageName);

}