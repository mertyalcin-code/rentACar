package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CarMaintananceDto;
import com.btkAkademi.rentACar.business.requests.carMaintananceRequest.CreateCarMaintananceRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarMaintananceDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintanance;
@Service
public class CarMaintananceManager implements CarMaintananceService{
	private CarMaintananceDao carMaintananceDao;
	private ModelMapperService modelMapperService;
	
	
	@Autowired
	public CarMaintananceManager(CarMaintananceDao carMaintananceDao, ModelMapperService modelMapperService) {
		super();
		this.carMaintananceDao = carMaintananceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CarMaintananceDto>> getAll() {
		List<CarMaintanance> carMaintananceList = this.carMaintananceDao.findAll();
		List<CarMaintananceDto> response = carMaintananceList.stream()
				.map(carMaintanance->modelMapperService.forDto()
				.map(carMaintanance, CarMaintananceDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarMaintananceDto>>(response);
	}

	@Override
	public Result add(@Valid CreateCarMaintananceRequest createCarMaintananceRequest) {
		

		CarMaintanance carMaintanance = this.modelMapperService.forRequest().map(createCarMaintananceRequest,CarMaintanance.class);
		this.carMaintananceDao.save(carMaintanance);		
		return new SuccessResult(Messages.carMaintananceAdded);
	}

	@Override
	public boolean checkIfCarNotInMaintanance(int carId) {
		if(carMaintananceDao.findByCarIdAndReturnDateIsNull(carId)!=null) {
			return true;
		}
		else return false;
}
}
