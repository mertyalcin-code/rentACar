package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.City;

public interface CityService {
	DataResult<List<CityListDto>> findAll();
	DataResult<CityListDto> findById(int id);
	
	Result add(CreateCityRequest createCityRequest);
	Result update(UpdateCityRequest updateCityRequest);
	Result delete(int id);

}
