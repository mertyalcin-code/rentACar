package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	DataResult<List<PaymentListDto>> findAll(int pageNo, int pageSize);

	DataResult<List<PaymentListDto>> findAllByRentalId(int id);

	DataResult<PaymentListDto> findById(int id);

	Result add(CreatePaymentRequest createPaymentRequest);

	Result update(UpdatePaymentRequest updatePaymentRequest);

	Result delete(int id);

}
