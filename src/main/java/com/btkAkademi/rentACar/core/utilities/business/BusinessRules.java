package com.btkAkademi.rentACar.core.utilities.business;

import com.btkAkademi.rentACar.core.utilities.results.Result;

public class BusinessRules {
	//takes business logic array and controls each of them. If there is an ErrorResult, then returns it 
	public static Result run(Result... logics) {
		for (Result logic : logics) {
			if (!logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
