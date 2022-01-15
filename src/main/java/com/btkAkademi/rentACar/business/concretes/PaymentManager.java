package com.btkAkademi.rentACar.business.concretes;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.adapters.banks.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
import com.btkAkademi.rentACar.entities.concretes.Car;
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
	private CustomerCardDetailService customerPaymentDetailService;
	
	//Dependency injection 
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, RentalService rentalService,
			CarService carService, AdditionalServiceService additionalServiceService,
			BankAdapterService bankAdapterService, CustomerCardDetailService customerPaymentDetailService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.additionalServiceService = additionalServiceService;
		this.bankAdapterService = bankAdapterService;
		this.customerPaymentDetailService = customerPaymentDetailService;
	}
	//Gets All Payments
	@Override
	public DataResult<List<PaymentListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Payment> paymentList = this.paymentDao.findAll(pageable).getContent();
		List<PaymentListDto> response = paymentList.stream().map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response);
	
	}
	//Gets All Payments for one rental
	@Override
	public DataResult<List<PaymentListDto>> findAllByRentalId(int id) {
		
		List<Payment> paymentList = this.paymentDao.getAllByRentalId(id); //id yoksa error veriyor
		List<PaymentListDto> response = paymentList.stream().map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response);
	}
	//finds specific payment 
	@Override
	public DataResult<PaymentListDto> getById(int id) {
		if(paymentDao.existsById(id)) {
			Payment payment= paymentDao.findById(id).get();
			PaymentListDto response = modelMapperService.forDto().map(payment,PaymentListDto.class);
			return new SuccessDataResult<PaymentListDto>(response);
		}
		else return new ErrorDataResult<>();
	}
	//adds a payment
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
		Result result = BusinessRules.run(
				bankAdapterService.checkIfLimitIsEnough(
						createPaymentRequest.getCardNo(),
						createPaymentRequest.getDay(),
						createPaymentRequest.getMonth(),
						createPaymentRequest.getCvv(),
						totalPrice));
		if (result != null) {
			return result;
		}
		
		//to avoid error
		payment.setId(0);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.paymentAdded);
	}
	//updates a payment
	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		//converts request to payment
		Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);	
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.paymentUpdated);
	}
	//deletes a payment
	@Override
	public Result delete(int id) {
		if(paymentDao.existsById(id)) {
			paymentDao.deleteById(id);
			return new SuccessResult(Messages.paymentDeleted);
		}
		return new ErrorResult();
	}

	//To calculate total price
	private double totalPriceCalculator(RentalListDto rental) {
		
		double totalPrice = 0.0;

		//finds usage day
		long days = ChronoUnit.DAYS.between( rental.getRentDate() , rental.getReturnDate()) ;
	
		//if  return date and rent date are equal than we charge one day
		if(days==0) days=1;
		//calculates total usage price by day
		totalPrice+=days* carService.findCarById(rental.getCarId()).getData().getDailyPrice();
		
		List<AdditionalServiceListDto> services = additionalServiceService.findAllByRentalId(rental.getId()).getData();
		//calculates total additional service price 
		for(AdditionalServiceListDto additionalService : services) {
			
			totalPrice+=additionalService.getPrice();
			
		}
		System.out.println(totalPrice);
		return totalPrice;
	}







	
}
