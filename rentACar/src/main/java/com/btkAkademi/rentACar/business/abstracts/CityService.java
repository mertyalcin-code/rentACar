package com.btkAkademi.rentACar.business.abstracts;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CityService {

	Result add(CreateCityRequest createCityRequest);

}
