package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.CustomerCardDetailListDto;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.CreateCustomerCardDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.UpdateCustomerCardDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CustomerCardDetailService {
	DataResult<List<CustomerCardDetailListDto>> findCustomerPaymentDetailsByCustomerId(int customerId);

	DataResult<CustomerCardDetailListDto> findById(int id);

	Result add(CreateCustomerCardDetailRequest createCustomerPaymentDetailRequest);

	Result update(UpdateCustomerCardDetailsRequest updateCustomerPamentDetails);

	Result delete(int id);
}
