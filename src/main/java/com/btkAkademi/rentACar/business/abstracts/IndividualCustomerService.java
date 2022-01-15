package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> findAll(int pageNo,int pageSize);
	DataResult<IndividualCustomerListDto> findById(int id);
	
	Result add (CreateIndividualCustomerRequest createIndividualCustomerRequest);
	Result update (UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	Result delete (int id);
}
