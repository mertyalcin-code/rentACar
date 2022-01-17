package com.btkAkademi.rentACar.business.concretes;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CarService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequest.CreateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.City;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService{
	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private CarService carService;
	private RentalService rentalService;
	private PaymentService paymentService;
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			CarService carService, RentalService rentalService, PaymentService paymentService,
			AdditionalServiceService additionalServiceService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.carService = carService;
		this.rentalService = rentalService;
		this.paymentService = paymentService;
		this.additionalServiceService = additionalServiceService;
	}



	@Override
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId) {
		Invoice invoice = invoiceDao.findByRentalId(rentalId);
		RentalListDto rental = rentalService.findById(rentalId).getData();
		IndividualCustomerListDto customer = individualCustomerService.findById(rental.getCustomerId()).getData();
		List<AdditionalServiceListDto> additionalServices = additionalServiceService.findAllByRentalId(rentalId).getData();
		CarListDto car = carService.findCarById(rental.getCarId()).getData();
		List<PaymentListDto> payments = paymentService.findAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for(PaymentListDto payment:payments) {
			totalPrice+=payment.getTotalPaymentAmount();
		}
		
		InvoiceIndividualCustomerDto responseCustomerDto = InvoiceIndividualCustomerDto.builder()
				.id(invoice.getId())
				.firstName(customer.getFirstName())
				.lastName(customer.getLastName())
				.nationalityId(customer.getNationalityId())
				.email(customer.getEmail())
				.totalPrice(totalPrice)
				.rentDate(rental.getRentDate())
				.returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreationDate())
				.additonalServices(additionalServices)
				.build();
		return null;
	}

	@Override
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result = BusinessRules.run(checkIfInvoiceAlreadyExists(createInvoiceRequest.getRentalId()));
		if (result != null) {
			return result;
		}
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest,Invoice.class);
		this.invoiceDao.save(invoice);		
		return new SuccessResult(Messages.invoiceAdded);
	}



	private Result checkIfInvoiceAlreadyExists(int rentalId) {
		if(invoiceDao.findByRentalId(rentalId)!=null) {
			return new ErrorResult(Messages.invoiceAlreadyExists);
		}
		return new SuccessResult();
	}







}
