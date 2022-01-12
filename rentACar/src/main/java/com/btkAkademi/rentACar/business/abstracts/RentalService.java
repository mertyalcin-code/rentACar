package com.btkAkademi.rentACar.business.abstracts;

import javax.validation.Valid;

import com.btkAkademi.rentACar.business.requests.createRentalRequest;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface RentalService {

	Result add( createRentalRequest createRentalRequest);
	

}
