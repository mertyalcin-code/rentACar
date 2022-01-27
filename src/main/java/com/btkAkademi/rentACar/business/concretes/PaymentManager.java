package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.adapters.banks.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PaymentDao;
import com.btkAkademi.rentACar.entities.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {

	// Dependencies

	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private CarService carService;
	private AdditionalServiceService additionalServiceService;
	private BankAdapterService bankAdapterService;
	private PromoCodeService promoCodeService;
	private AdditionalServiceItemService additionalServiceItemService;

	// Dependency injection
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService,
			@Lazy RentalService rentalService, CarService carService, AdditionalServiceService additionalServiceService,
			BankAdapterService bankAdapterService, PromoCodeService promoCodeService,
			AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
		this.carService = carService;
		this.additionalServiceService = additionalServiceService;
		this.bankAdapterService = bankAdapterService;
		this.promoCodeService = promoCodeService;
		this.additionalServiceItemService = additionalServiceItemService;
	}

	// Gets All Payments
	@Override
	public DataResult<List<PaymentListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<Payment> paymentList = this.paymentDao.findAll(pageable).getContent();
		List<PaymentListDto> response = paymentList.stream()
				.map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response, Messages.PAYMENTLIST);

	}

	// Gets All Payments for one rental
	@Override
	public DataResult<List<PaymentListDto>> findAllByRentalId(int id) {

		List<Payment> paymentList = this.paymentDao.getAllByRentalId(id); // id yoksa error veriyor
		List<PaymentListDto> response = paymentList.stream()
				.map(payment -> modelMapperService.forDto().map(payment, PaymentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<>(response, Messages.PAYMENTLIST);
	}

	// finds specific payment
	@Override
	public DataResult<PaymentListDto> findById(int id) {
		if (paymentDao.existsById(id)) {
			Payment payment = paymentDao.findById(id).get();
			PaymentListDto response = modelMapperService.forDto().map(payment, PaymentListDto.class);
			return new SuccessDataResult<PaymentListDto>(response, Messages.PAYMENTLIST);
		} else
			return new ErrorDataResult<>();
	}

	// To Show Customer Total Amount Before Payment
	@Override
	public DataResult<Double> calculateTotalPriceForDisplay(CalculateTotalPriceRequest calculateTotalPriceRequest) {
		RentalListDto rental = rentalService.findById(calculateTotalPriceRequest.getRentalId()).getData();
		System.out.println(rental.getRentDate());
		Double price = this.totalPriceCalculator(rental, calculateTotalPriceRequest.getReturnDate());
		System.out.println(price);
		return new SuccessDataResult<Double>(price, Messages.TOTALPRICECALCULATE);
	}

	// adds a payment
	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		createPaymentRequest.setPaymentTime(LocalDate.now());
		// converts request to payment
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);

		int rentalId = createPaymentRequest.getRentalId();

		// finds related rental from database
		RentalListDto rental = rentalService.findById(rentalId).getData();

		// calculates total amount
		double totalPrice = totalPriceCalculator(rental, createPaymentRequest.getReturnDate());

		payment.setTotalPaymentAmount(totalPrice);

		// Bussiness logic
		Result result = BusinessRules.run(bankAdapterService.makePayment(createPaymentRequest.getCardNo(),
				createPaymentRequest.getYear(), createPaymentRequest.getMonth(), createPaymentRequest.getCvv(),
				totalPrice));
		if (result != null) {
			return result;
		}

		// to avoid error
		payment.setId(0);

		this.paymentDao.save(payment);

		return new SuccessResult(Messages.PAYMENTADD);
	}

	// updates a payment
	@Override
	public Result update(UpdatePaymentRequest updatePaymentRequest) {
		// converts request to payment
		Payment payment = this.modelMapperService.forRequest().map(updatePaymentRequest, Payment.class);

		this.paymentDao.save(payment);

		return new SuccessResult(Messages.PAYMENTUPDATE);
	}

	// deletes a payment
	@Override
	public Result delete(int id) {
		if (paymentDao.existsById(id)) {
			paymentDao.deleteById(id);
			return new SuccessResult(Messages.PAYMENTDELETE);
		}
		return new ErrorResult();
	}

	// To calculate total price
	private double totalPriceCalculator(RentalListDto rental, LocalDate returnDate) {

		double totalPrice = 0.0;
		System.out.println(rental.getRentDate() + " " + returnDate);
		// finds usage day
		long days = ChronoUnit.DAYS.between(rental.getRentDate(), returnDate);

		// if return date and rent date are equal than we charge one day
		if (days == 0)
			days = 1;
		// calculates total usage price by day
		totalPrice += days * carService.findById(rental.getCarId()).getData().getDailyPrice();

		// discount
		if (rental.getPromoCodeId() != 0) {
			PromoCodeDto promoCode = promoCodeService.findById(rental.getPromoCodeId()).getData();
			if (!promoCode.getEndDate().isBefore(LocalDate.now())) {
				double discountRate = 0;
				discountRate = promoCode.getDiscountRate();
				totalPrice = totalPrice - (totalPrice * discountRate);
			}
		}
		// calculates total additional service price
		List<AdditionalServiceListDto> services = additionalServiceService.findAllByRentalId(rental.getId()).getData();
		for (AdditionalServiceListDto additionalService : services) {
			double additionalServiceItemPrice = additionalServiceItemService
					.findById(additionalService.getAdditionalServiceItemId()).getData().getPrice();
			totalPrice += additionalServiceItemPrice;
		}

		return totalPrice;
	}

}
