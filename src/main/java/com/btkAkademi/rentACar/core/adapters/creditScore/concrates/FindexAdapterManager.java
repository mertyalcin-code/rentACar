package com.btkAkademi.rentACar.core.adapters.creditScore.concrates;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.core.adapters.creditScore.abstracts.CreditScoreAdapterService;
import com.btkAkademi.rentACar.core.externalServices.creditScoreServices.FindexFake;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;

@Service
public class FindexAdapterManager implements CreditScoreAdapterService {

	@Override
	public DataResult<Integer> getScoreOfIndividualCustomer(String nationalityId) {
		FindexFake findexFake = new FindexFake();
		return new SuccessDataResult<Integer>(findexFake.getScoreOfIndividualCustomer(nationalityId));
	}

	@Override
	public DataResult<Integer> getScoreOfCorporateCustomer(String taxNumber) {
		FindexFake findexFake = new FindexFake();
		return new SuccessDataResult<Integer>(findexFake.getScoreOfIndividualCustomer(taxNumber));
	}

}
