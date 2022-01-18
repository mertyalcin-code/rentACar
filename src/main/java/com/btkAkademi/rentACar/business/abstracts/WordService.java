package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.WordSearchListDto;
import com.btkAkademi.rentACar.business.requests.wordRequests.CreateWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.DeleteWordRequest;
import com.btkAkademi.rentACar.business.requests.wordRequests.UpdateWordRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface WordService {
	DataResult<WordSearchListDto> getByKey(String key);

	DataResult<List<WordSearchListDto>> getAll();

	Result add(CreateWordRequest createWordRequest);

	Result delete(DeleteWordRequest deleteWordRequest);

	Result update(UpdateWordRequest updateWordRequest);
}