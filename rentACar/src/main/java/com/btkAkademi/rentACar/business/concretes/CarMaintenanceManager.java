package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintenanceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintenanceDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;
@Service
public class CarMaintenanceManager implements CarMaintenanceService{
	private CarMaintenanceDao carMaintananceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	
	@Autowired

	public CarMaintenanceManager(CarMaintenanceDao carMaintananceDao, ModelMapperService modelMapperService,
			RentalService rentalService) {
		super();
		this.carMaintananceDao = carMaintananceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}


	@Override
	public DataResult<List<CarMaintenanceDto>> getAll() {
		List<CarMaintenance> carMaintananceList = this.carMaintananceDao.findAll();
		List<CarMaintenanceDto> response = carMaintananceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintenanceDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintenanceDto>>(response);
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintananceRequest) {
		Result result = BusinessRules.run(
				checkIfCarIsRented(createCarMaintananceRequest.getCarId()))	;
		
		if(result!=null) {			
			return result;
		}
		
		CarMaintenance carMaintanance = this.modelMapperService.forRequest()
				.map(createCarMaintananceRequest,CarMaintenance.class);
		carMaintanance.setId(0);
		System.out.println(carMaintanance.getId());
	
		this.carMaintananceDao.save(carMaintanance);		
		return new SuccessResult(Messages.carMaintananceAdded);
	}

	@Override
	public boolean checkIfCarIsInMaintanance(int carId) {
		if(carMaintananceDao.findByCarIdAndMaintenanceEndIsNull(carId)!=null) {
			return true;
		}
		else return false;
	}
	
	private Result checkIfCarIsRented(int carId) {
		if(rentalService.isCarRented(carId)) {
			return new ErrorResult(Messages.carRented);
		}
		else return new SuccessResult();
	}
}
