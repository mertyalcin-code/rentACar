package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.constants.Role;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
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
	private UserService userService;

	// Dependency Injection
	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			@Lazy RentalService rentalService, PaymentService paymentService,
			AdditionalServiceService additionalServiceService,
			AdditionalServiceItemService additionalServiceItemService, UserService userService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.rentalService = rentalService;
		this.paymentService = paymentService;
		this.additionalServiceService = additionalServiceService;
		this.additionalServiceItemService = additionalServiceItemService;
		this.userService = userService;
	}

	// prepares a dto with the information required for the invoice
	@Override
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId) {
		Result result = BusinessRules.run(checkIfRentalIsFinished(rentalId), checkIfPaymentIsMade(rentalId),
				checkIfInvoiceExistsByRentalId(rentalId), checkIfCustomerIndividual(rentalId));
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}

		Invoice invoice = invoiceDao.findByRentalId(rentalId);
		RentalListDto rental = rentalService.findById(rentalId).getData();
		IndividualCustomerListDto customer = individualCustomerService.findById(rental.getCustomerId()).getData();

		List<AdditionalServiceListDto> additionalServices = additionalServiceService.findAllByRentalId(rentalId)
				.getData();
		List<AdditionalServiceItemListDto> additionalServiceItems = new ArrayList<AdditionalServiceItemListDto>();
		double itemPrices = 0;
		for (AdditionalServiceListDto additionalServiceListDto : additionalServices) {
			itemPrices += additionalServiceItemService.findById(additionalServiceListDto.getAdditionalServiceItemId())
					.getData().getPrice();
			additionalServiceItems.add(additionalServiceItemService
					.findById(additionalServiceListDto.getAdditionalServiceItemId()).getData());
		}

		List<PaymentListDto> payments = paymentService.findAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for (PaymentListDto payment : payments) {
			totalPrice += payment.getTotalPaymentAmount();
		}
		double rentPrice = totalPrice - itemPrices;

		InvoiceIndividualCustomerDto responseCustomerDto = InvoiceIndividualCustomerDto.builder().id(invoice.getId())
				.firstName(customer.getFirstName()).lastName(customer.getLastName())
				.nationalityNo(customer.getNationalityNo()).email(customer.getEmail()).totalPrice(totalPrice)
				.rentDate(rental.getRentDate()).returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreationDate()).additionalServiceItems(additionalServiceItems)
				.rentPrice(rentPrice).build();
		return new SuccessDataResult<InvoiceIndividualCustomerDto>(responseCustomerDto, Messages.INVOICELIST);
	}

	// prepares a dto with the information required for the invoice
	@Override
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId) {
		Result result = BusinessRules.run(checkIfRentalIsFinished(rentalId), checkIfPaymentIsMade(rentalId),
				checkIfInvoiceExistsByRentalId(rentalId), checkIfCustomerCorporate(rentalId)

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
		double itemPrices = 0;
		for (AdditionalServiceListDto additionalServiceListDto : additionalServices) {
			itemPrices += additionalServiceItemService.findById(additionalServiceListDto.getAdditionalServiceItemId())
					.getData().getPrice();
			additionalServiceItems.add(additionalServiceItemService
					.findById(additionalServiceListDto.getAdditionalServiceItemId()).getData());
		}

		List<PaymentListDto> payments = paymentService.findAllByRentalId(rentalId).getData();
		double totalPrice = 0;
		for (PaymentListDto payment : payments) {
			totalPrice += payment.getTotalPaymentAmount();
		}
		double rentPrice = totalPrice - itemPrices;

		InvoiceCorporateCustomerDto responseCustomerDto = InvoiceCorporateCustomerDto.builder().id(invoice.getId())
				.companyName(customer.getCompanyName()).taxNumber(customer.getTaxNumber()).email(customer.getEmail())
				.totalPrice(totalPrice).rentDate(rental.getRentDate()).returnedDate(rental.getReturnDate())
				.creationDate(invoice.getCreationDate()).additionalServiceItems(additionalServiceItems)
				.rentPrice(rentPrice).build();
		return new SuccessDataResult<InvoiceCorporateCustomerDto>(responseCustomerDto, Messages.INVOICELIST);
	}

	// Finds all invoices
	@Override
	public DataResult<List<InvoiceListDto>> findAll() {
		List<Invoice> invoiceList = invoiceDao.findAll();
		List<InvoiceListDto> response = invoiceList.stream().map(

				invoice ->

				modelMapperService.forDto().map(invoice, InvoiceListDto.class)

		).collect(Collectors.toList());
		return new SuccessDataResult<List<InvoiceListDto>>(response, Messages.INVOICELIST);
	}

	// Creates an invoice request
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Result result = BusinessRules.run(checkIfInvoiceAlreadyExists(createInvoiceRequest.getRentalId()));
		if (result != null) {
			return result;
		}
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setCreationDate(LocalDate.now());
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.INVOICEADD);
	}

	// Updates an invoice request
	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccessResult(Messages.INVOICEUPDATE);
	}

	// Deletes an invoice request
	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfInvoiceExistsById(id));
		if (result != null) {
			return result;
		}
		invoiceDao.deleteById(id);
		return new SuccessResult(Messages.INVOICEDELETE);

	}
	// Helpers

	// There can be only one invoice
	private Result checkIfInvoiceAlreadyExists(int rentalId) {
		if (invoiceDao.findByRentalId(rentalId) != null) {
			return new ErrorResult(Messages.INVOICENUMBERAlREADYEXISTS);
		}
		return new SuccessResult();
	}

	// controls there is a invoice or not
	private Result checkIfInvoiceExistsByRentalId(int rentalId) {
		if (invoiceDao.findByRentalId(rentalId) == null) {
			return new ErrorResult(Messages.INVOICENOTFOUND);
		}
		return new SuccessResult();
	}

	// Checks the car rental is finished
	private Result checkIfRentalIsFinished(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			if (rentalService.findById(rentalId).getData().getReturnDate() == null) {
				return new ErrorResult(Messages.RENTALNOTFINISHED);
			} else
				return new SuccessResult();
		} else
			return new ErrorResult(Messages.RENTALNOTFOUND);
	}

	// Checks the car rental is finished
	private Result checkIfPaymentIsMade(int rentalId) {
		if (rentalService.findById(rentalId).getData() != null) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.PAYMENTNOTFOUND);
	}

	private Result checkIfCustomerIndividual(int rentalId) {
		int customerId = rentalService.findById(rentalId).getData().getCustomerId();
		if (userService.findById(customerId).getData().getRole().equals(Role.INDIVIDUAL_CUSTOMER.getRole())) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.NOTFOUND);
	}

	private Result checkIfCustomerCorporate(int rentalId) {
		int customerId = rentalService.findById(rentalId).getData().getCustomerId();

		if (userService.findById(customerId).getData().getRole().equals(Role.CORPORATE_CUSTOMER.getRole())) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.NOTFOUND);
	}

	private Result checkIfInvoiceExistsById(int id) {
		if (invoiceDao.existsById(id)) {
			return new SuccessResult();
		} else
			return new ErrorResult(Messages.INVOICENOTFOUND);
	}

}
