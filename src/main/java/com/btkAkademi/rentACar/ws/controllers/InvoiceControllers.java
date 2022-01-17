package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.InvoiceService;
import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequest.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceControllers {
	private InvoiceService invoiceService;
	@Autowired
	public InvoiceControllers(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}
	@GetMapping("get-invoice-for-corporate-customer/{rentalId}")
	public DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(@PathVariable int rentalId) {
		return this.invoiceService.getInvoiceForCorporateCustomer(rentalId);
	}
	@GetMapping("get-invoice-for-individual-customer/{rentalId}")
	public DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(@PathVariable int rentalId) {
		return this.invoiceService.getInvoiceForIndividualCustomer(rentalId);
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {

		return this.invoiceService.add(createInvoiceRequest);
	}
}
