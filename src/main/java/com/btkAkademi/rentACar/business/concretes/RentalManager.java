package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.creditScore.abstracts.CreditScoreAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
	// Dependencies
	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;
	private CarMaintenanceService carMaintananceService;
	private CityService cityService;
	private CreditScoreAdapterService creditScoreAdapterService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private CarService carService;

	// Dependency Injection
	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,
			CarMaintenanceService carMaintananceService, CityService cityService,
			CreditScoreAdapterService creditScoreAdapterService, IndividualCustomerService individualCustomerService,
			CorporateCustomerService corporateCustomerService, CarService carService) {
		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
		this.carMaintananceService = carMaintananceService;
		this.cityService = cityService;
		this.creditScoreAdapterService = creditScoreAdapterService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.carService = carService;
	}

	// Lists all rentals
	@Override
	public DataResult<List<RentalListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}

	// Lists all rentals for one customer
	@Override
	public DataResult<List<RentalListDto>> findAllByCustomerId(int id) {
		// customer yoksa hata versin
		List<Rental> rentalList = this.rentalDao.findAllByCustomerId(id);
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response);
	}

	// finds specific rental
	@Override
	public DataResult<RentalListDto> findById(int id) {
		if (rentalDao.existsById(id)) {
			RentalListDto response = modelMapperService.forDto().map(rentalDao.findById(id).get(), RentalListDto.class);

			return new SuccessDataResult<>(response);
		}

		else
			return new ErrorDataResult<>();
	}

	// Adds a new rental
	@Override
	public Result addForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfIsCarInMaintanance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(createRentalRequest.getCarId()),
				checkIfIndividualCustomerHasEnoughCreditScore(
						individualCustomerService.findById(createRentalRequest.getCustomerId()).getData()
								.getNationalityId(),
						carService.findCarById(createRentalRequest.getCarId()).getData().getFindexScore()),
				checkIfCustomerAgeIsEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId())

		);

		if (result != null) {
			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		// if we don't this ReturnedKilometer will be 0
		rental.setReturnedKilometer(null);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}

	@Override
	public Result addForCorporateCustomer(CreateRentalRequest createRentalRequest) {
		Result result = BusinessRules.run(checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfIsCarInMaintanance(createRentalRequest.getCarId()),
				checkIfCityExist(createRentalRequest.getPickUpCityId()),
				checkIfCityExist(createRentalRequest.getReturnCityId()),
				checkIfIsCarAlreadyRented(createRentalRequest.getCarId()),
				checkIfCorporateCustomerHasEnoughCreditScore(
						corporateCustomerService.findById(createRentalRequest.getCustomerId()).getData().getTaxNumber(),
						carService.findCarById(createRentalRequest.getCarId()).getData().getFindexScore()));

		if (result != null) {
			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		// if we don't this ReturnedKilometer will be 0
		rental.setReturnedKilometer(null);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalAdded);
	}

	// Updates rental
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Result result = BusinessRules.run(checkIfCustomerExist(updateRentalRequest.getCustomerId()),
				checkIfCityExist(updateRentalRequest.getPickUpCityId()),
				checkIfCityExist(updateRentalRequest.getReturnCityId()),
				checkIfKilometersAreCorrect(updateRentalRequest.getRentedKilometer(),
						updateRentalRequest.getReturnedKilometer()),
				checkIfDatesAreCorrect(updateRentalRequest.getRentDate(), updateRentalRequest.getReturnDate()));

		if (result != null) {
			return result;
		}

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.rentalUpdated);
	}

	// delete rental
	@Override
	public Result delete(int id) {
		if (rentalDao.existsById(id)) {
			rentalDao.deleteById(id);
			return new SuccessResult(Messages.rentalDeleted);
		}
		return new ErrorResult();
	}

	// controls is car actively rented
	@Override
	public boolean isCarRented(int carId) {
		if (rentalDao.findByCarIdAndReturnDateIsNull(carId) != null) {
			return true;
		} else
			return false;
	}

	// Helpers

	// Dates validation
	private Result checkIfDatesAreCorrect(LocalDate rentDate, LocalDate returnDate) {
		if (!rentDate.isBefore(returnDate)) {
			return new ErrorResult(Messages.returnDateShouldBeAfterTheRentDate);

		}

		return new SuccessResult();
	}

	// Kilometer validation
	private Result checkIfKilometersAreCorrect(int rentedKilometer, int returnedKilometer) {
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

	// checks if car is in maintanance
	private Result checkIfIsCarInMaintanance(int carId) {
		if (carMaintananceService.isCarInMaintenance(carId)) {
			return new ErrorResult(Messages.carInMaintanance);
		}
		return new SuccessResult();
	}

	// checks if car is already rented
	private Result checkIfIsCarAlreadyRented(int carId) {
		if (isCarRented(carId)) {
			return new ErrorResult(Messages.carRented);
		}
		return new SuccessResult();
	}

	// checks if individual customer have enough credit score to rent this car
	private Result checkIfIndividualCustomerHasEnoughCreditScore(String nationalityId, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (creditScoreAdapterService.getScoreOfIndividualCustomer(nationalityId).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.lowCreditScore);

	}

	// checks if corporate customer have enough credit score to rent this car
	private Result checkIfCorporateCustomerHasEnoughCreditScore(String taxNumber, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (creditScoreAdapterService.getScoreOfCorporateCustomer(taxNumber).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.lowCreditScore);

	}

	// checks if individual customer have enough age to rent this car
	private Result checkIfCustomerAgeIsEnough(int customerId, int carId) {

		int age = Period
				.between(individualCustomerService.findById(customerId).getData().getBirthDate(), LocalDate.now())
				.getYears();
		int minAge = carService.findCarById(carId).getData().getMinAge();
		if (age < minAge) {
			return new ErrorResult(Messages.ageIsNotEnough);
		}
		return new SuccessResult();

	}
}
