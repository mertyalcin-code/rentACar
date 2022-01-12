package com.btkAkademi.rentACar.business.abstracts;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

}
