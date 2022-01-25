package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.cityRequests.CreateCityRequest;
import com.btkAkademi.rentACar.business.requests.cityRequests.UpdateCityRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService {
	// Dependencies
	private CityDao cityDao;
	private ModelMapperService modelMapperService;

	// Dependency Injeciton
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		super();
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	// lists all cities in the database
	@Override
	public DataResult<List<CityListDto>> findAll() {

		List<City> cityList = this.cityDao.findAllByOrderByCityNameAsc();
		List<CityListDto> response = cityList.stream()
				.map(city -> modelMapperService.forDto().map(city, CityListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CityListDto>>(response, Messages.CITYLIST);
	}

	// Finds city with that id
	@Override
	public DataResult<CityListDto> findById(int id) {
		Result result = BusinessRules.run(checkIfCityIdExist(id));
		if (result != null) {
			return new ErrorDataResult<CityListDto>(result.getMessage());
		}
		City city = cityDao.findById(id).get();
		CityListDto response = modelMapperService.forDto().map(city, CityListDto.class);
		return new SuccessDataResult<CityListDto>(response, Messages.CITYLIST);
	}

	// Creates a new city in the database
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		Result result = BusinessRules.run(checkIfCityNameExists(createCityRequest.getCityName()));
		if (result != null) {
			return result;
		}
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(Messages.CITYADD);
	}

	// update
	@Override
	public Result update(UpdateCityRequest updateCityRequest) {

		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);
		return new SuccessResult(Messages.CITYUPDATE);
	}

	// delete
	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCityIdExist(id));
		if (result != null) {
			return new ErrorDataResult<CityListDto>(result.getMessage());
		}

		cityDao.deleteById(id); //if there is a relation it will give error 
		return new SuccessResult(Messages.CITYDELETE);

	}

	// Helpers

	// Checks city is added before or not
	private Result checkIfCityNameExists(String cityName) {
		if (cityDao.findByCityName(cityName) != null) {
			return new ErrorResult(Messages.CITYNAMEEXISTS);
		}
		return new SuccessResult();
	}

	// controls that city exists or not
	private Result checkIfCityIdExist(int id) {
		if (!cityDao.existsById(id)) {
			return new ErrorResult(Messages.CITYNOTFOUND);
		}
		return new SuccessResult();
	}

}
