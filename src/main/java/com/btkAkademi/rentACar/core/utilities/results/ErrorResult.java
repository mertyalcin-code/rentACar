package com.btkAkademi.rentACar.core.utilities.results;

//Custom response with error 
public class ErrorResult extends Result {
	public ErrorResult() {
		super(false);
	}

	public ErrorResult(String message) {
		super(false, message);
	}
}