package com.btkAkademi.rentACar.core.utilities.adapters.banks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.exernals.banks.IsBank;
@Service
public class IsBankAdapterManager implements BankAdapterService{
	
	@Override
	public Result checkIfLimitIsEnough(String cardNo,String day,String mounth,String cVV,double amount) {	
		IsBank isBank = new IsBank();
		if( isBank.isLimitExists(cardNo,day,mounth,cVV,amount)) {
			return new SuccessResult();
		}else {	
			return new ErrorResult(Messages.limitNotEnough);
			}

			
		
	}

	


}
