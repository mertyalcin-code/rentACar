package com.btkAkademi.rentACar.core.utilities.adapters.banks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.exernals.banks.IsBankManager;
@Service
public class IsBankAdapterManager implements BankAdapterService{
	private IsBankManager isBankManager;
	@Autowired
	public IsBankAdapterManager(IsBankManager isBankManager) {
		super();
		this.isBankManager = isBankManager;
	}
	@Override
	public boolean checkIfLimitIsEnough(String cardNo, double limit) {
		
		return isBankManager.isLimitExists(cardNo, limit);
	}

	


}
