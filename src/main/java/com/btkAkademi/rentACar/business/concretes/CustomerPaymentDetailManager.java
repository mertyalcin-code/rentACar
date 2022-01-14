package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerPaymentDetailService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.CustomerPaymentDetailListDto;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.customerPaymentDetailRequest.CreateCustomerPaymentDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerPaymentDetailRequest.UpdateCustomerPamentDetails;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerPaymentDetailDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CustomerPaymentDetail;
@Service
public class CustomerPaymentDetailManager implements CustomerPaymentDetailService{
	private ModelMapperService modelMapperService;
	private CustomerPaymentDetailDao customerPaymentDetailDao;
	
	@Autowired
	public CustomerPaymentDetailManager(ModelMapperService modelMapperService,
			CustomerPaymentDetailDao customerPaymentDetailDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.customerPaymentDetailDao = customerPaymentDetailDao;
	}
	//lists payment for one paymentDetail
	@Override
	public DataResult<List<CustomerPaymentDetailListDto>> findCustomerPaymentDetailsByCustomerId(int customerId) {
		if(customerPaymentDetailDao.existsById(customerId)) {
			List<CustomerPaymentDetail> customerPaymentDetails = customerPaymentDetailDao.findByCustomerId(customerId);
			List<CustomerPaymentDetailListDto> response = customerPaymentDetails.stream().map(customerPaymentDetail -> modelMapperService.forDto().map(customerPaymentDetail, CustomerPaymentDetailListDto.class))
					.collect(Collectors.toList());

			return new SuccessDataResult<>(response);
		}
		return new ErrorDataResult<>();
	}

	//Find Payment by id
	@Override
	public DataResult<CustomerPaymentDetailListDto> findById(int id) {
		if(customerPaymentDetailDao.existsById(id)) {
			CustomerPaymentDetail customerPaymentDetail = customerPaymentDetailDao.findById(id).get();
			CustomerPaymentDetailListDto response = modelMapperService.forDto().map(customerPaymentDetail, CustomerPaymentDetailListDto.class);
			return new SuccessDataResult<CustomerPaymentDetailListDto>(response);
		}
		return new ErrorDataResult<CustomerPaymentDetailListDto>();
	}

	//adds new credit cart info
	@Override
	public Result add(CreateCustomerPaymentDetailRequest createCustomerPaymentDetailRequest) {
		CustomerPaymentDetail customerPaymentDetail = this.modelMapperService.forRequest().map(createCustomerPaymentDetailRequest, CustomerPaymentDetail.class);
		this.customerPaymentDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.customerPaymentDetailAdded);
	}
	//updates a credit cart info
	@Override
	public Result update(UpdateCustomerPamentDetails updateCustomerPamentDetails) {
		CustomerPaymentDetail customerPaymentDetail = this.modelMapperService.forRequest().map(updateCustomerPamentDetails, CustomerPaymentDetail.class);
		this.customerPaymentDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.customerPaymentDetailUpdated);
	}
	//deletes a credit cart info
	@Override
	public Result delete(int id) {
	if(customerPaymentDetailDao.existsById(id)) {
		customerPaymentDetailDao.deleteById(id);
		return new SuccessResult(Messages.customerPaymentDetailDeleted);
	}
		return new ErrorResult();
	}


	
}
