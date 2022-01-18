package com.btkAkademi.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequest.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequest.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.InvoiceDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {

	// Dependencies
	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	private RentalService rentalService;
	private PaymentService paymentService;
	private AdditionalServiceService additionalServiceService;
	private AdditionalServiceItemService additionalServiceItemService;

	// Dependency Injection
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			RentalService rentalService, PaymentService paymentService,
			AdditionalServiceService additionalServiceService,
			AdditionalServiceItemService additionalServiceItemService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.rentalService = rentalService;
		this.paymentService = paymentService;
		this.additionalServiceService = additionalServiceService;
		this.additionalServiceItemService = additionalServiceItemService;
	}


	// prepares a dto with the information required for the invoice
	@Override
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId) {
		Result result = BusinessRules.run(checkIfRentalIsFinished(rentalId), checkIfPaymentIsMade(rentalId),checkIfInvoiceExistsByRentalId(rentalId));
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}
		Invoice invoice = invoiceDao.findByRentalId(rentalId);
		RentalListDto rental = rentalService.findById(rentalId).getData();
		IndividualCustomerListDto customer = individualCustomerService.findById(rental.getCustomerId()).getData();
		List<AdditionalServiceListDto> additionalServices = additionalServiceService.findAllByRentalId(rentalId).getData();
		List<AdditionalServiceItemListDto> additionalServiceItems = new ArrayList<AdditionalServiceItemListDto>();
		for(AdditionalServiceListDto additionalServiceListDto: additionalServices) {
			additionalServiceItems.add(additionalServiceItemService.findById(additionalServiceListDto.getAdditionalServiceItemId()).getData());
		}
		
		
		List<PaymentListDto> payments = paymentService.findAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for (PaymentListDto payment : payments) {
			totalPrice += payment.getTotalPaymentAmount();
		}

		InvoiceIndividualCustomerDto responseCustomerDto = InvoiceIndividualCustomerDto.builder().id(invoice.getId())
				.firstName(customer.getFirstName()).lastName(customer.getLastName())
				.nationalityId(customer.getNationalityNo()).email(customer.getEmail()).totalPrice(totalPrice)
				.rentDate(rental.getRentDate()).returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreationDate()).additonalServiceItems(additionalServiceItems).build();
		return new SuccessDataResult<InvoiceIndividualCustomerDto>(responseCustomerDto);
	}


	// prepares a dto with the information required for the invoice
	@Override
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId) {
		Result result = BusinessRules.run(checkIfRentalIsFinished(rentalId),
				checkIfPaymentIsMade(rentalId),
				checkIfInvoiceExistsByRentalId(rentalId)
				
				);
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}
		Invoice invoice = invoiceDao.findByRentalId(rentalId);
		RentalListDto rental = rentalService.findById(rentalId).getData();
		CorporateCustomerListDto customer = corporateCustomerService.findById(rental.getCustomerId()).getData();
		List<AdditionalServiceListDto> additionalServices = additionalServiceService.findAllByRentalId(rentalId)
				.getData();
		List<AdditionalServiceItemListDto> additionalServiceItems = new ArrayList<AdditionalServiceItemListDto>();
		for(AdditionalServiceListDto additionalServiceListDto: additionalServices) {
			additionalServiceItems.add(additionalServiceItemService.findById(additionalServiceListDto.getAdditionalServiceItemId()).getData());
		}
		List<PaymentListDto> payments = paymentService.findAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for (PaymentListDto payment : payments) {
			totalPrice += payment.getTotalPaymentAmount();
		}

		InvoiceCorporateCustomerDto responseCustomerDto = InvoiceCorporateCustomerDto.builder().id(invoice.getId())
				.companyName(customer.getCompanyName()).taxNumber(customer.getTaxNumber()).email(customer.getEmail())
				.totalPrice(totalPrice).rentDate(rental.getRentDate()).returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreationDate())
				.additonalServiceItems(additionalServiceItems)
				.build();
		return new SuccessDataResult<InvoiceCorporateCustomerDto>(responseCustomerDto);
	}

	// Creates an invoice request
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result = BusinessRules.run(checkIfInvoiceAlreadyExists(createInvoiceRequest.getRentalId()));
		if (result != null) {
			return result;
		}
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceAdded);
	}

	// Updates an invoice request
	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.invoiceUpdated);
	}

	// Deletes an invoice request
	@Override
	public Result delete(int id) {
		if (invoiceDao.existsById(id)) {
			invoiceDao.deleteById(id);
			return new SuccessResult(Messages.invoiceDeleted);
		} else
			return new ErrorResult();
	}
	// Helpers

	// There can be only one invoice
	private Result checkIfInvoiceAlreadyExists(int rentalId) {
		if (invoiceDao.findByRentalId(rentalId) != null) {
			return new ErrorResult(Messages.invoiceAlreadyExists);
		}
		return new SuccessResult();
	}
	//controls there is a invoice or not
	private Result checkIfInvoiceExistsByRentalId(int rentalId) {
		if (invoiceDao.findByRentalId(rentalId) == null) {
			return new ErrorResult(Messages.invoiceNotCreated);
		}
		return new SuccessResult();
	}

	// Checks the car rental is finished
	private Result checkIfRentalIsFinished(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			if (rentalService.findById(rentalId).getData().getReturnDate() == null) {
				return new ErrorResult(Messages.rentalIsNotFinished);
			} else
				return new SuccessResult();
		} else
			return new ErrorResult(Messages.notFound);
	}

	// Checks the car rental is finished
	private Result checkIfPaymentIsMade(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.paymentNotFound);
	}

}
