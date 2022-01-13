package com.btkAkademi.rentACar.business.concretes;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintananceService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.rentalRequest.createRentalRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	// Dependencies
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintananceService carMaintananceService;
	// Dependency Injection
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,
			CarMaintananceService carMaintananceService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintananceService = carMaintananceService;
	}


	


	// Lists all retals

	
	// Adds a new rental
	@Override
	public Result add(createRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(
				checkIfDatesCorrect(createRentalRequest.getRentDate(), createRentalRequest.getReturnDate()),
				checkIfKilometerCorrect(createRentalRequest.getRentedKilometer(),createRentalRequest.getReturnedKilometer()),
				checkIfCustomerExist(createRentalRequest.getCustomer().getId()),
				checkIfCarInMaintanance(createRentalRequest.getCar().getId())
				);

		if (result != null) {
			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}


	// Helpers

	// Dates validation
	private Result checkIfDatesCorrect(LocalDate rentDate, LocalDate returnDate) {
		if (!returnDate.isAfter(rentDate)) {
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
	//check
	private Result checkIfCarInMaintanance(int carId) {
		if(carMaintananceService.checkIfCarIsInMaintanance(carId)) {
			return new ErrorResult(Messages.carInMaintanance);
		}
		return new SuccessResult();
	}
}
