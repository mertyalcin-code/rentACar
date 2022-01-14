package com.btkAkademi.rentACar.core.utilities.adapters.banks;

public class TestBankAdapter implements BankAdapterService{

	@Override
	public boolean checkIfLimitIsEnough(String cardNo, double limit) {		
		return true;
	}
	
	
}
