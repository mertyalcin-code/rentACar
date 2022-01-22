package com.btkAkademi.rentACar.core.utilities.results;

//Custom response
public class Result {
	private boolean success;
	private String message;

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public String getMessage() {
		return get(this.message);
	}
	
	// for language
	private String get(String message){
		if(!LanguageSelector.languageSelector(message).equals("")) {
			return LanguageSelector.languageSelector(message);
		}else return "mesaj hatası";
		
	}
}
