package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add (CreateIndividualCustomerRequest createIndividualCustomerRequest);
}
