package com.btkAkademi.rentACar.core.utilities.adapters.banks.concrates;

import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.adapters.banks.abstracts.BankAdapterService;
import com.btkAkademi.rentACar.core.utilities.externalServices.banks.IsBank;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;

@Service
public class IsBankAdapterManager implements BankAdapterService {

	@Override
	public Result checkIfLimitIsEnough(String cardNo, String day, String mounth, String cVV, double amount) {
		IsBank isBank = new IsBank();
		if (isBank.isLimitExists(cardNo, day, mounth, cVV, amount)) {
			return new SuccessResult();
		} else {
			return new ErrorResult(Messages.limitNotEnough);
		}

	}

}
