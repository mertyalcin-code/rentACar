package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CarMaintenanceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CityService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.MyRentalListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalAddResponse;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;
import com.btkAkademi.rentACar.business.requests.rentalRequests.UpdateRentalRequest;
import com.btkAkademi.rentACar.core.adapters.creditScore.abstracts.CreditScoreAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.RentalDao;
import com.btkAkademi.rentACar.entities.concretes.City;
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
	private PaymentService paymentService;
	private InvoiceService invoiceService;
	// Dependency Injection
	@Autowired

	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CustomerService customerService,
			CarMaintenanceService carMaintananceService, CityService cityService,
			CreditScoreAdapterService creditScoreAdapterService, IndividualCustomerService individualCustomerService,
			CorporateCustomerService corporateCustomerService, CarService carService, PaymentService paymentService,
			InvoiceService invoiceService) {
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
		this.paymentService = paymentService;
		this.invoiceService = invoiceService;
	}

	// Lists all rentals
	@Override
	public DataResult<List<RentalListDto>> findAll(int pageNo, int pageSize) {	
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Rental> rentalList = this.rentalDao.findAll(pageable).getContent();
		List<RentalListDto> response = rentalList.stream()
				.map(rental -> modelMapperService.forDto().map(rental, RentalListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentalListDto>>(response,Messages.LIST);
	}

	// Lists all rentals for one customer
	@Override
	public DataResult<List<MyRentalListDto>> findAllByCustomerId(int id) {
		// customer yoksa hata versin
		List<Rental> rentalList = this.rentalDao.findAllByCustomerId(id);
		List<MyRentalListDto> response = new ArrayList<MyRentalListDto>();
		for(Rental rental: rentalList) {
			MyRentalListDto responseItem = new MyRentalListDto();
			responseItem.setRentalId(rental.getId());
			responseItem.setRentDate(rental.getRentDate());
			responseItem.setBrandName(carService.findCarById(rental.getCar().getId()).getData().getBrandName());
			responseItem.setCarName(carService.findCarById(rental.getCar().getId()).getData().getCarName());
			responseItem.setPickUpCityName(rental.getPickUpCity().getCityName());
			if(paymentService.findAllByRentalId(rental.getId()).isSuccess()) {
				double totalPrice=0;
				List<PaymentListDto> payments = paymentService.findAllByRentalId(rental.getId()).getData();
				for(PaymentListDto payment:payments) {
					totalPrice+=payment.getTotalPaymentAmount();
				}
				responseItem.setTotalPayment(totalPrice);
			}
			if(isRentalFinished(rental.getCar().getId())) {
				responseItem.setRentalFinished(true);
				responseItem.setReturnCityName(rental.getReturnCity().getCityName());
		
				if(invoiceService.getInvoiceForIndividualCustomer(rental.getId()).isSuccess()
						|| invoiceService.getInvoiceForCorporateCustomer(rental.getId()).isSuccess()
						) {
					responseItem.setReturnDate(rental.getReturnDate());
					responseItem.setInvoiceCreated(true);
				}
			}
			else {
				responseItem.setRentalFinished(false);
				responseItem.setInvoiceCreated(false);
			}
			response.add(responseItem);
		}
		
		return new SuccessDataResult<List<MyRentalListDto>>(response,Messages.LIST);
	}

	// finds specific rental
	@Override
	public DataResult<RentalListDto> findById(int id) {

		if (rentalDao.existsById(id)) {
			RentalListDto response = modelMapperService.forDto().map(rentalDao.findById(id).get(), RentalListDto.class);

			return new SuccessDataResult<>(response,Messages.LIST);
		}
		else return new ErrorDataResult<>(Messages.RENTALNOTFOUND);
		

	}

	// Adds a new rental
	@Override
	public DataResult<RentalAddResponse>	 addForIndividualCustomer(CreateRentalRequest createRentalRequest) {
		CarListDto wantedCar = carService.findCarById(createRentalRequest.getCarId()).getData();
		if (!checkIfIsCarInMaintanance(createRentalRequest.getCarId()).isSuccess()
				|| !checkIfIsCarAlreadyRented(createRentalRequest.getCarId()).isSuccess()) {
			CarListDto car = findAvailableCar(wantedCar.getSegmentId(), wantedCar.getCityId()).getData();

			if (car != null) {
				createRentalRequest.setCarId(car.getId());
			} else
				return new ErrorDataResult<RentalAddResponse>(Messages.NOAVAILABLECARINTHISSEGMENT);
		}
		Result result = BusinessRules.run( // nationaly id hatası alıyoruz customer yok ise
				checkIfCustomerExist(createRentalRequest.getCustomerId()),
				checkIfIndividualCustomerHasEnoughCreditScore(
						individualCustomerService.findById(createRentalRequest.getCustomerId()).getData()
								.getNationalityNo(),
						carService.findCarById(createRentalRequest.getCarId()).getData().getFindexScore()),
				checkIfCustomerAgeIsEnough(createRentalRequest.getCustomerId(), createRentalRequest.getCarId())

		);

		if (result != null) {
			return new ErrorDataResult<RentalAddResponse>(result.getMessage())	;
		}

		CarListDto car = carService.findCarById(createRentalRequest.getCarId()).getData();

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		// if we don't this ReturnedKilometer will be 0

		rental.setReturnedKilometer(null);
		rental.setRentedKilometer(car.getKilometer());
		City pickUpCity = modelMapperService.forRequest().map(cityService.findById(car.getCityId()).getData(),
				City.class);
		System.out.println(pickUpCity.getId());
		rental.setPickUpCity(pickUpCity);
		this.rentalDao.save(rental);
		
		return new SuccessDataResult<RentalAddResponse>(new RentalAddResponse(rental.getId(), rental.getCar().getId()), Messages.RENTALADD);
	}

	@Override
	public DataResult<RentalAddResponse>	 addForCorporateCustomer(CreateRentalRequest createRentalRequest) {
		CarListDto wantedCar = carService.findCarById(createRentalRequest.getCarId()).getData();
		if (!checkIfIsCarInMaintanance(createRentalRequest.getCarId()).isSuccess()
				|| !checkIfIsCarAlreadyRented(createRentalRequest.getCarId()).isSuccess()) {
			CarListDto car = findAvailableCar(wantedCar.getSegmentId(), wantedCar.getCityId()).getData();
			if (car != null) {
				createRentalRequest.setCarId(car.getId());

			} else
				return new ErrorDataResult<RentalAddResponse>(Messages.NOAVAILABLECARINTHISSEGMENT);
		}
		Result result = BusinessRules.run(checkIfCustomerExist(createRentalRequest.getCustomerId()),

				checkIfCorporateCustomerHasEnoughCreditScore(
						corporateCustomerService.findById(createRentalRequest.getCustomerId()).getData().getTaxNumber(),
						carService.findCarById(createRentalRequest.getCarId()).getData().getFindexScore()));
		if (result != null) {
			return new ErrorDataResult<RentalAddResponse>(result.getMessage())	;
		}
		CarListDto car = carService.findCarById(createRentalRequest.getCarId()).getData();
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		// if we don't this ReturnedKilometer will be 0
		rental.setReturnedKilometer(null);
		rental.setRentedKilometer(car.getKilometer());
		City pickUpCity = modelMapperService.forRequest().map(cityService.findById(car.getCityId()).getData(),
				City.class);
		rental.setPickUpCity(pickUpCity);
		this.rentalDao.save(rental);
		return new SuccessDataResult<RentalAddResponse>(new RentalAddResponse(rental.getId(), rental.getCar().getId()), Messages.RENTALADD);
	}

	// Updates rental
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rentalFromDb = rentalDao.findById(updateRentalRequest.getId()).get();
		Result result = BusinessRules.run(checkIfCityExist(updateRentalRequest.getReturnCityId()),
				checkIfKilometersAreCorrect(rentalFromDb.getRentedKilometer(),
						updateRentalRequest.getReturnedKilometer()),
				checkIfDatesAreCorrect(rentalFromDb.getRentDate(), updateRentalRequest.getReturnDate()));
		if (result != null) {
			return result;
		}

		carService.updateCarCity(rentalFromDb.getCar().getId(), updateRentalRequest.getReturnCityId());
		carService.updateCarKilometer(rentalFromDb.getCar().getId(), updateRentalRequest.getReturnedKilometer());

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		rental.setRentDate(rentalFromDb.getRentDate());
		rental.setCar(rentalFromDb.getCar());
		rental.setRentedKilometer(rentalFromDb.getRentedKilometer());
		rental.setCustomer(rentalFromDb.getCustomer());
		rental.setPickUpCity(rentalFromDb.getPickUpCity());
		rental.setPromoCode(rentalFromDb.getPromoCode());
		this.rentalDao.save(rental);
		return new SuccessResult(Messages.RENTALUPDATE);
	}

	// delete rental
	@Override
	public Result delete(int id) {
		if (rentalDao.existsById(id)) {
			rentalDao.deleteById(id);
			return new SuccessResult(Messages.RENTALDELETE);
		}
		return new ErrorResult(Messages.RENTALNOTFOUND);
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
			return new ErrorResult(Messages.RENTALDATEERROR);

		}

		return new SuccessResult();
	}

	// Kilometer validation
	private Result checkIfKilometersAreCorrect(int rentedKilometer, int returnedKilometer) {
		if (rentedKilometer > returnedKilometer) {
			return new ErrorResult(Messages.KILOMETERERROR);
		}

		return new SuccessResult();
	}

	// Checks customer exist in the database
	private Result checkIfCustomerExist(int customerId) {
		if (!customerService.findCustomerById(customerId).isSuccess()) {
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}

		return new SuccessResult();
	}

	// checks is there a city with that id
	private Result checkIfCityExist(int cityId) {
		if (!cityService.findById(cityId).isSuccess()) {
			return new ErrorResult(Messages.CITYNOTFOUND);
		}
		return new SuccessResult();
	}

	// checks if car is in maintanance
	private Result checkIfIsCarInMaintanance(int carId) {
		if (carMaintananceService.isCarInMaintenance(carId)) {
			return new ErrorResult(Messages.CARINMANTANANCE);
		}
		return new SuccessResult();
	}

	// checks if car is already rented
	private Result checkIfIsCarAlreadyRented(int carId) {
		if (isCarRented(carId)) {
			return new ErrorResult(Messages.CARRENTED);
		}
		return new SuccessResult();
	}

	// checks if individual customer have enough credit score to rent this car
	private Result checkIfIndividualCustomerHasEnoughCreditScore(String nationalityId, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (creditScoreAdapterService.getScoreOfIndividualCustomer(nationalityId).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.RENTALFINDEXSCOREERROR);

	}

	// checks if corporate customer have enough credit score to rent this car
	private Result checkIfCorporateCustomerHasEnoughCreditScore(String taxNumber, int minCreditScore) {
		System.out.println("min :" + minCreditScore);
		if (creditScoreAdapterService.getScoreOfCorporateCustomer(taxNumber).getData() >= minCreditScore) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.RENTALFINDEXSCOREERROR);

	}

	// checks if individual customer have enough age to rent this car
	private Result checkIfCustomerAgeIsEnough(int customerId, int carId) {

		int age = Period
				.between(individualCustomerService.findById(customerId).getData().getBirthDate(), LocalDate.now())
				.getYears();
		int minAge = carService.findCarById(carId).getData().getMinAge();
		if (age < minAge) {
			return new ErrorResult(Messages.AGENOTENOUGH);
		}
		return new SuccessResult();
	}

	//
	private DataResult<CarListDto> findAvailableCar(int SegmentId, int cityId) {
		if (carService.findAvailableCarsBySegmentId(SegmentId, cityId).isSuccess()) {
			CarListDto car = carService
					.findCarById(carService.findAvailableCarsBySegmentId(SegmentId, cityId).getData().get(0)).getData();
			return new SuccessDataResult<CarListDto>(car);
		} else
			return new ErrorDataResult<CarListDto>();
	}

	@Override
	public DataResult<RentalListDto> findActiveRentalByCarId(int id) {
		if(rentalDao.findByCarIdAndReturnDateIsNull(id)!=null) {
			
			RentalListDto response = modelMapperService.forDto().map(rentalDao.findByCarIdAndReturnDateIsNull(id), RentalListDto.class);
			return new SuccessDataResult<RentalListDto>(response,Messages.RENTALLIST);
		}else return new ErrorDataResult<RentalListDto>(Messages.RENTALNOTFOUND);
	
	}
	private boolean isRentalFinished(int rentalId) {
		if (this.findById(rentalId).getData() != null) {
			if (this.findById(rentalId).getData().getReturnDate() == null) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
