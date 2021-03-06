package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerCardDetailService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CustomerCardDetailListDto;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.CreateCustomerCardDetailRequest;
import com.btkAkademi.rentACar.business.requests.customerCardDetailRequests.UpdateCustomerCardDetailsRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerCardDetailDao;
import com.btkAkademi.rentACar.entities.concretes.CustomerCardDetail;

@Service
public class CustomerCardDetailManager implements CustomerCardDetailService { // kart zaten var uyarısı eklenebilir.
	// Dependencies
	private ModelMapperService modelMapperService;
	private CustomerCardDetailDao customerCardDetailDao;

	// Dependency Injection
	@Autowired
	public CustomerCardDetailManager(ModelMapperService modelMapperService,
			CustomerCardDetailDao customerCardDetailDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.customerCardDetailDao = customerCardDetailDao;
	}

	// lists payment for one paymentDetail
	@Override
	public DataResult<List<CustomerCardDetailListDto>> findCustomerCardDetailsByCustomerId(int customerId) {

		List<CustomerCardDetail> customerPaymentDetails = customerCardDetailDao.findAllByCustomerId(customerId);
		List<CustomerCardDetailListDto> response = customerPaymentDetails.stream()
				.map(customerPaymentDetail -> modelMapperService.forDto().map(customerPaymentDetail,
						CustomerCardDetailListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<>(response, Messages.CREDITCARDLIST);

	}

	// Find Payment by id
	@Override
	public DataResult<CustomerCardDetailListDto> findById(int id) {
		Result result = BusinessRules.run(checkIfCardIdExists(id));
		if (result != null) {
			return new ErrorDataResult<CustomerCardDetailListDto>(result.getMessage());
		}
		CustomerCardDetail customerPaymentDetail = customerCardDetailDao.findById(id).get();
		CustomerCardDetailListDto response = modelMapperService.forDto().map(customerPaymentDetail,
				CustomerCardDetailListDto.class);
		return new SuccessDataResult<CustomerCardDetailListDto>(response, Messages.CREDITCARDLIST);

	}

	// adds new credit cart info
	@Override
	public Result add(CreateCustomerCardDetailRequest createCustomerPaymentDetailRequest) {
		Result result = BusinessRules.run(checkIfCardNoExist(createCustomerPaymentDetailRequest.getCardNo()));
		if (result != null) {
			return result;
		}
		CustomerCardDetail customerPaymentDetail = this.modelMapperService.forRequest()
				.map(createCustomerPaymentDetailRequest, CustomerCardDetail.class);
		this.customerCardDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.CREDITCARDADD);
	}

	// updates a credit cart info
	@Override
	public Result update(UpdateCustomerCardDetailsRequest updateCustomerPamentDetails) {
		CustomerCardDetail customerPaymentDetail = this.modelMapperService.forRequest().map(updateCustomerPamentDetails,
				CustomerCardDetail.class);
		this.customerCardDetailDao.save(customerPaymentDetail);
		return new SuccessResult(Messages.CREDITCARDUPDATE);
	}

	// deletes a credit cart info
	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCardIdExists(id));
		if (result != null) {
			return result;
		}
		customerCardDetailDao.deleteById(id);
		return new SuccessResult(Messages.CREDITCARDELETE);

	}

	private Result checkIfCardNoExist(String cardNo) {
		if (customerCardDetailDao.findByCardNo(cardNo) != null) {
			return new ErrorResult(Messages.CREDITCARDALREADYEXISTS);
		} else
			return new SuccessResult();
	}

	// Checks is there a card with that id
	private Result checkIfCardIdExists(int id) {
		if (!this.customerCardDetailDao.existsById(id)) {
			return new ErrorResult(Messages.CREDITCARDNOTFOUND);
		}
		return new SuccessResult();
	}

}
