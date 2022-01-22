package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.InvoiceCorporateCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceIndividualCustomerDto;
import com.btkAkademi.rentACar.business.dtos.InvoiceListDto;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.CreateInvoiceRequest;
import com.btkAkademi.rentACar.business.requests.invoiceRequests.UpdateInvoiceRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface InvoiceService {

	DataResult<InvoiceIndividualCustomerDto> getInvoiceForIndividualCustomer(int rentalId);

	DataResult<InvoiceCorporateCustomerDto> getInvoiceForCorporateCustomer(int rentalId);

	Result add(CreateInvoiceRequest createInvoiceRequest);

	Result update(UpdateInvoiceRequest updateInvoiceRequest);

	Result delete(int id);

	DataResult<List<InvoiceListDto>> findAll();


}
