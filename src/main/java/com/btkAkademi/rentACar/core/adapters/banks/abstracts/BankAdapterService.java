package com.btkAkademi.rentACar.core.adapters.banks.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BankAdapterService {

	Result checkIfLimitIsEnough(String cardNo, String day, String mounth, String cVV, double amount);
}
