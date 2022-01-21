package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.MyRentalListDto;
import com.btkAkademi.rentACar.business.dtos.RentalAddResponse;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface RentalService {
	DataResult<List<RentalListDto>> findAll(int pageNo, int pageSize);

	DataResult<List<MyRentalListDto>> findAllByCustomerId(int id);

	DataResult<RentalListDto> findById(int id);

DataResult<RentalAddResponse>	 addForIndividualCustomer(CreateRentalRequest createRentalRequest);

DataResult<RentalAddResponse>	 addForCorporateCustomer(CreateRentalRequest createRentalRequest);

	Result update(UpdateRentalRequest updateRentalRequest);

	Result delete(int id);

	boolean isCarRented(int carId);

	DataResult<RentalListDto> findActiveRentalByCarId(int id);

}
