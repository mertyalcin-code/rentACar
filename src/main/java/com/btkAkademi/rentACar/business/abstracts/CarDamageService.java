package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarDamageListDto;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.CreateCarDamageRequest;
import com.btkAkademi.rentACar.business.requests.carDamageRequest.UpdateCarDamageRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CarDamageService {
	DataResult<List<CarDamageListDto>> getAllByCarId(int id);
	DataResult<CarDamageListDto> findById(int id);
	
	Result add(CreateCarDamageRequest createCarDamageRequest);
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	Result delete(int id);

}
