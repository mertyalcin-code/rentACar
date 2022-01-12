package com.btkAkademi.rentACar.business.requests.corporateCustomerRequest;

import javax.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
	private String email;
	private String password;	
	private String companyName;
	private String taxNumber;
}
