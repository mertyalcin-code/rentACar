package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequest.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequest.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface PaymentService {
	DataResult<List<PaymentListDto>> getAll(int pageNo,int pageSize);
	DataResult<List<PaymentListDto>> getAllByRentalId(int id);
	DataResult<PaymentListDto> getById(int id);
	
	Result add(CreatePaymentRequest createPaymentRequest);
	Result update(UpdatePaymentRequest updatePaymentRequest);
	Result delete(int id);

}
