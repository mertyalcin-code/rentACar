package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.TranslationSearchListDto;
import com.btkAkademi.rentACar.business.requests.translationRequests.CreateTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.DeleteTranslationRequest;
import com.btkAkademi.rentACar.business.requests.translationRequests.UpdateTranslationRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Translation;

public interface TranslationService {
	DataResult<List<TranslationSearchListDto>> getAll();

	Result add(CreateTranslationRequest createTranslationRequest);

	Result delete(DeleteTranslationRequest deleteTranslationRequest);

	Result update(UpdateTranslationRequest updateTranslationRequest);

	Translation getTranslationByLanguage_IdAndWord_Id(int languageId, int wordId);
}