package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

public interface CarMaintenanceService {

	DataResult<List<CarMaintenanceDto>> getAll();

	Result add(CreateCarMaintenanceRequest createCarMaintananceRequest);
	
	boolean checkIfCarIsInMaintanance (int carId) ;
}
