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
import com.btkAkademi.rentACar.business.dtos.CarMaintenanceListDto;
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
	//Dependencies
	private CarMaintenanceDao carMaintananceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	
	//Dependency injection
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintananceDao, ModelMapperService modelMapperService,
			RentalService rentalService) {
		super();
		this.carMaintananceDao = carMaintananceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	//Lists cars in maintenance or cars that have been maintained before
	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() {
		List<CarMaintenance> carMaintananceList = this.carMaintananceDao.findAll();
		List<CarMaintenanceListDto> response = carMaintananceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response);
	}
	//Sends the car to maintenance
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
	
	//Returns true if car is in maintenance
	@Override
	public boolean checkIfCarIsInMaintanance(int carId) {
		if(carMaintananceDao.findByCarIdAndMaintenanceEndIsNull(carId)!=null) {
			return true;
		}
		else return false;
	}	
	//Checks if car is actively rented 
	private Result checkIfCarIsRented(int carId) {
		if(rentalService.isCarRented(carId)) {
			return new ErrorResult(Messages.carRented);
		}
		else return new SuccessResult();
	}
}
