package com.btkAkademi.rentACar.exernals.banks;

import org.springframework.stereotype.Component;

@Component
public class IsBankManager {
	
	public boolean isLimitExists(String cardNo,double amount) {
		return true;
	}
}
