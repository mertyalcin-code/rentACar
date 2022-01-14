package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.UpdateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

public interface CarMaintenanceService {

	DataResult<List<CarMaintenanceListDto>> getAll();
	DataResult<List<CarMaintenanceListDto>> getAllByCarId(int id);
	DataResult<CarMaintenanceListDto> findById(int id);
	
	Result add(CreateCarMaintenanceRequest createCarMaintananceRequest);
	Result update(UpdateCarMaintananceRequest updateCarMaintananceRequest);
	Result delete(int id);
	
	Result checkIfCarIsInMaintanance (int carId) ;

}
