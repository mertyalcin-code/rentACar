package com.btkAkademi.rentACar.core.utilities.results;

//Custom response with success
public class SuccessResult extends Result {
	public SuccessResult() {
		super(true);
	}

	public SuccessResult(String message) {
		super(true, message);
	}
}
