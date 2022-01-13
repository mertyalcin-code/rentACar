package com.btkAkademi.rentACar.business.concretes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.cityRequest.CreateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;
@Service
public class CityManager implements CityService{
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(@Valid CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run(checkIfCityNameExists(createCityRequest.getCityName()));
		if (result != null) {
			return result;
		}
		City city = this.modelMapperService.forRequest().map(createCityRequest,City.class);
		this.cityDao.save(city);		
		return new SuccessResult(Messages.carAdded);
	}
	private Result checkIfCityNameExists(String cityName) {
		if(cityDao.findByCityName(cityName)!=null) {
			return new ErrorResult(Messages.cityNameExists);
		}
		return new SuccessResult();
	}
}
