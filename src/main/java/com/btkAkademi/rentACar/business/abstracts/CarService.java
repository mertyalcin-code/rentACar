package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarService {
	
	DataResult<List<CarListDto>> getAll(int pageNo, int pageSize);
	DataResult<List<CarListDto>> getAllByBrandId(int brandId,int pageNo, int pageSize);
	DataResult<List<CarListDto>> getAllByColorId(int colorId,int pageNo,int pageSize);
	DataResult<CarListDto> findCarById(int id);
	
	Result add(CreateCarRequest createCarRequest);	
	Result update(UpdateCarRequest updateCarRequest);
	Result delete( int id);

	
	

	
}
