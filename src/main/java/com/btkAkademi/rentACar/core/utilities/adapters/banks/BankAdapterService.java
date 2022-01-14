package com.btkAkademi.rentACar.core.utilities.adapters.banks;

public interface BankAdapterService {
	
	boolean checkIfLimitIsEnough(String cardNo,double limit);
}
