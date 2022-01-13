package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;
	@Service
	public class CityManager implements CityService{
	//Dependencies
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	//Dependency Injeciton
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}
	//lists all cities in the database
	@Override
	public DataResult<List<CityListDto>> getAll() {
			
		List<City> cityList = this.cityDao.findAll();
		List<CityListDto> response = cityList.stream()
				.map(city->modelMapperService.forDto()
				.map(city, CityListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityListDto>>(response);
	}

	//Creates a new city in the database
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run(checkIfCityNameExists(createCityRequest.getCityName()));
		if (result != null) {
			return result;
		}
		City city = this.modelMapperService.forRequest().map(createCityRequest,City.class);
		this.cityDao.save(city);		
		return new SuccessResult(Messages.carAdded);
	}
	//Helpers
	
	//Checks city is added before or not
	private Result checkIfCityNameExists(String cityName) {
		if(cityDao.findByCityName(cityName)!=null) {
			return new ErrorResult(Messages.cityNameExists);
		}
		return new SuccessResult();
	}


}
