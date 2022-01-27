package com.btkAkademi.rentACar.core.adapters.banks.conctrates;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.adapters.banks.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.externalServices.banks.IsBankFakePos;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class IsBankAdapterManager implements BankAdapterService {

	@Override
	public Result makePayment(String cardNo, String day, String mounth, String cVV, double amount) {
		IsBankFakePos isBank = new IsBankFakePos();
		if (isBank.posService(cardNo, day, mounth, cVV, amount)) {
			return new SuccessResult();
		} else {
			return new ErrorResult(Messages.NOTFOUND);
		}

	}

}
