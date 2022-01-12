package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.requests.carRequest.CreateCarRequest;
import com.btkAkademi.rentACar.business.requests.carRequest.UpdateCarRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
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
	// Lists all cars
	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> carList = this.carDao.findAll();
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
