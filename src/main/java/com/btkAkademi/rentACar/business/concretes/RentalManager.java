package com.btkAkademi.rentACar.business.concretes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequest.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequest.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Color;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	// Dependencies
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintenanceService carMaintananceService;
	private CityService cityService;

	
	// Dependency Injection
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,
			CarMaintenanceService carMaintananceService, CityService cityService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintananceService = carMaintananceService;
		this.cityService = cityService;
	}
	
	// Lists all rentals
	@Override
	public DataResult<List<RentalListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);		
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}
	
	// Lists all rentals for one customer
	@Override
	public DataResult<List<RentalListDto>> getAllByCustomerId(int id) {
		//customer yoksa hata versin
		List<Rental> rentalList = this.rentalDao.getAllByCustomerId(id);
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}
	//finds specific rental
	@Override
	public DataResult<RentalListDto> findById(int id) {
		if(rentalDao.existsById(id)) {
			RentalListDto response = modelMapperService.forDto().map(rentalDao.findById(id).get(), RentalListDto.class);
			
			return new SuccessDataResult<>(response);
		}
		
		else return new ErrorDataResult<>();
	}
	// Adds a new rental
	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkIfCustomerExist(createRentalRequest.getCustomerId()),
				carMaintananceService.checkIfCarIsInMaintanance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId()),
				checkIfCarIsRented(createRentalRequest.getCarId())
				);

		if (result != null) {
			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		rental.setReturnedKilometer(null);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}
	// Updates rental
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result = BusinessRules.run(
				checkIfCustomerExist(updateRentalRequest.getCustomerId()),				
				checkIfCityExist(updateRentalRequest.getPickUpCityId()),
				checkIfCityExist(updateRentalRequest.getReturnCityId()),			
				checkIfKilometerCorrect(updateRentalRequest.getRentedKilometer(), updateRentalRequest.getReturnedKilometer()),
				checkIfDatesCorrect(updateRentalRequest.getRentDate(),updateRentalRequest.getReturnDate())
				);

		if (result != null) {
			return result;
		}
		
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalUpdated);
	}
	//delete rental
	@Override
	public Result delete(int id) {
		if(rentalDao.existsById(id)) {
			rentalDao.deleteById(id);
			return new SuccessResult(Messages.rentalDeleted);
		}
		return new ErrorResult();
	}

	//controls is car actively rented
	@Override
	public Result checkIfCarIsRented(int carId) {
		if (rentalDao.findByCarIdAndReturnDateIsNull(carId) != null) {
			return new ErrorResult(Messages.carRented);
		} else
			return new SuccessResult();
	}
	
	// Helpers

	// Dates validation
	private Result checkIfDatesCorrect(LocalDate rentDate, LocalDate returnDate) {
		if (!rentDate.isBefore(returnDate)) {
			return new ErrorResult(Messages.returnDateShouldBeAfterTheRentDate);

		}

		return new SuccessResult();
	}

	// Kilometer validation
	private Result checkIfKilometerCorrect(int rentedKilometer, int returnedKilometer) {
		if (rentedKilometer > returnedKilometer) {
			return new ErrorResult(Messages.returnedKilometerShouldNotBeLowerThanRentedKilometer);
		}

		return new SuccessResult();
	}

	// Checks customer exist in the database
	private Result checkIfCustomerExist(int customerId) {
		if (!customerService.findCustomerById(customerId).isSuccess()) {
			return new ErrorResult(Messages.customerNotFound);
		}

		return new SuccessResult();
	}
	
	// checks is there a city with that id 
		private Result checkIfCityExist(int cityId) {
			if (!cityService.findById(cityId).isSuccess()) {
				return new ErrorResult(Messages.carInMaintanance);
			}
			return new SuccessResult();
		}

	


}
