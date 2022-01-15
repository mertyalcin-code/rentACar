package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {
	DataResult<List<RentalListDto>>  findAll(int pageNo,int pageSize);
	DataResult<List<RentalListDto>>  findAllByCustomerId(int id);
	DataResult<RentalListDto> findById(int id);
	
	Result add(CreateRentalRequest createRentalRequest);	
	Result update(UpdateRentalRequest updateRentalRequest);
	Result delete(int id);
	
	boolean isCarRented(int carId);

	
}
