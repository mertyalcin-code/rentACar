package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequest.createRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalService {
	DataResult<List<RentalListDto>>  getAll(int pageNo,int pageSize);
	Result add(createRentalRequest createRentalRequest);	
	Result checkIfCarIsRented(int carId);
	DataResult<RentalListDto> findById(int id);
	
}
