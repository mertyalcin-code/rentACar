package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarMaintananceDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

public interface CarMaintananceService {

	DataResult<List<CarMaintananceDto>> getAll();

	Result add(@Valid CreateCarMaintananceRequest createCarMaintananceRequest);
	
	boolean checkIfCarIsInMaintanance (int carId) ;
}
