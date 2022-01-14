package com.btkAkademi.rentACar.business.concretes;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CustomerPaymentDetailService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequest.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.banks.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Payment;
import com.btkAkademi.rentACar.entities.concretes.Rental;
@Service
public class PaymentManager implements PaymentService{

	//Dependencies 
	
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;	
	private RentalService rentalService;
	private CarService carService;
	private AdditionalServiceService additionalServiceService;
	private BankAdapterService bankAdapterService;
	private CustomerPaymentDetailService customerPaymentDetailService;
	
	//Dependency injection 
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService,
			CarService carService, AdditionalServiceService additionalServiceService,
			BankAdapterService bankAdapterService, CustomerPaymentDetailService customerPaymentDetailService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.additionalServiceService = additionalServiceService;
		this.bankAdapterService = bankAdapterService;
		this.customerPaymentDetailService = customerPaymentDetailService;
	}
	
	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {	
		//converts request to payment
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		
		int rentalId= createPaymentRequest.getRentalId();
		
		//finds related rental from database
		RentalListDto rental = rentalService.findById(rentalId).getData();
	
		//calculates total amount
		double totalPrice = totalPriceCalculator(rental);
		
		payment.setTotalPaymentAmount(totalPrice);
		
		//Bussiness logic
		Result result = BusinessRules.run(bankAdapterService.checkIfLimitIsEnough("123456","","","",totalPrice));
		if (result != null) {
			return result;
		}
		
		//to avoid error
		payment.setId(0);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.rentalAdded);
	}
	


	private double totalPriceCalculator(RentalListDto rental) {
		
		double totalPrice = 0.0;

		//finds usage day
		long days = ChronoUnit.DAYS.between( rental.getRentDate() , rental.getReturnDate()) ;
	
		//if  return date and rent date are equal than we charge one day
		if(days==0) days=1;
		//calculates total usage price by day
		totalPrice+=days* carService.findCarById(rental.getCarId()).getData().getDailyPrice();
		
		List<AdditionalServiceDto> services = additionalServiceService.getAllByRentalId(rental.getId()).getData();
		//calculates total additional service price 
		for(AdditionalServiceDto additionalService : services) {
			
			totalPrice+=additionalService.getPrice();
			
		}
		System.out.println(totalPrice);
		return totalPrice;
	}



	
}
