package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests.UpdateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {

	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);

	Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest);

	Result delete(int id);

	DataResult<AdditionalServiceItemListDto> findById(int id);

	DataResult<List<AdditionalServiceItemListDto>> findAll();
}
