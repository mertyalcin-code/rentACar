package com.btkAkademi.rentACar.business.abstracts;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditionalServiceRequest createAdditionalService);

}