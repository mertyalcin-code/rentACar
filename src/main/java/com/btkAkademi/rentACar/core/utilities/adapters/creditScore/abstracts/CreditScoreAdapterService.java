package com.btkAkademi.rentACar.core.utilities.adapters.creditScore.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface CreditScoreAdapterService {
	DataResult<Integer> getScoreOfIndividualCustomer(String nationalityId) ;
	DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber);
}
