package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerService {
	DataResult<List<IndividualCustomerListDto>> getAll(int pageNo,int pageSize);
	Result add (CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
