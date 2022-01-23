package com.btkAkademi.rentACar.business.constants;

public enum Role {
	EMPLOYEE("EMPLOYEE"),
	INDIVIDUAL_CUSTOMER("INDIVIDUAL_CUSTOMER"),
	CORPORATE_CUSTOMER("CORPORATE_CUSTOMER");
	
	
	
	private String role;
	
	 Role(String role) {
	        this.role = role;
	    }
	 public String getRole() {
	        return role;
	    }
}
