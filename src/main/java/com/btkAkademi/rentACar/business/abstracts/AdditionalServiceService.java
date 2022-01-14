package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.AdditionalServiceDto;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalService);
	DataResult<List<AdditionalServiceDto>> getAllByRentalId(int rentalId);
}
