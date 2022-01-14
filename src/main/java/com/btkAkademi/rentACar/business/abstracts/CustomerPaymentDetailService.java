package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.CustomerPaymentDetailListDto;
import com.btkAkademi.rentACar.business.requests.customerPaymentDetailRequest.CreateCustomerPaymentDetailRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerPaymentDetailDao;

public interface CustomerPaymentDetailService {

	Result add(CreateCustomerPaymentDetailRequest createCustomerPaymentDetailRequest);
	DataResult<List<CustomerPaymentDetailListDto>> findCustomerPaymentDetailsByCustomerId(int customerId);
}
