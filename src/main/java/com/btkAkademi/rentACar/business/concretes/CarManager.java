package com.btkAkademi.rentACar.business.concretes;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Color;

@Service
public class CarManager implements CarService {
	// Dependencies
	private CarDao carDao;
	private ModelMapperService modelMapperService;
	// Dependency Injection
	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}
	// Lists all cars with pageNo and Page Size
	@Override
	public DataResult<List<CarListDto>> getAll(int pageNo, int pageSize) {	
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);		
		List<Car> carList = this.carDao.findAll(pageable).getContent();
		List<CarListDto> response = carList.stream()
				.map(car->modelMapperService.forDto()
				.map(car, CarListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	// Adds a new car
	@Override
	public Result add(CreateCarRequest createCarRequest) {
	
		Car car = this.modelMapperService.forRequest().map(createCarRequest,Car.class);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.carAdded);
	}
	// Updates current car
	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		
		Result result = BusinessRules.run(
				checkIfCarIdExists(updateCarRequest.getId()));
		
		if(result!=null) {
			
			return result;
		}
	
		Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
		
		this.carDao.save(car);		
		return new SuccessResult(Messages.carUpdated);
	}
	//Finds Car by id
	@Override
	public DataResult<Car> findCarById(int id) {
		if(carDao.existsById(id)) {
			return new SuccessDataResult<Car>(carDao.findById(id).get());
		}
		else return new ErrorDataResult<Car>();
	}
	
	//Helpers
	
	//Checks is there a car with that id
	private Result checkIfCarIdExists(int id)
	{
		   if(!this.carDao.existsById(id)) {
			   
			   return new ErrorResult(Messages.carIdNotExists);
		   }
		   return new SuccessResult();
	}




	
}
