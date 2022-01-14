package com.btkAkademi.rentACar.core.utilities.adapters.banks;

import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

public class TestBankAdapter implements BankAdapterService{

	@Override
	public Result checkIfLimitIsEnough(String cardNo,String day,String mounth,String cVV,double amount) {		
		return new SuccessResult();
	}
	
	
}
