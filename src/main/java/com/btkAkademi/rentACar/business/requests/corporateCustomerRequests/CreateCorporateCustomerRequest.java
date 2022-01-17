package com.btkAkademi.rentACar.business.requests.corporateCustomerRequests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRequest {
	@Email
	@NotBlank
	private String email;
	@Size(min = 4, max = 30)
	private String password;
	@Size(min = 3, max = 255)
	private String companyName;
	@Size(min = 4, max = 255)
	private String taxNumber;
}
