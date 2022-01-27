package com.btkAkademi.rentACar.core.adapters.banks.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public interface BankAdapterService {

	Result makePayment(String cardNo, String year, String mounth, String cVV, double amount);
}
