package com.btkAkademi.rentACar.business.requests.individualCustomerRequests;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	
	@Email
	@NotBlank
	private String email;
	@Size(min = 4, max = 30)
	private String password;
	@Size(min = 2, max = 100)
	private String firstName;
	@Size(min = 2, max = 100)
	private String lastName;
	@NotNull
	private LocalDate birthDate;
	private String nationalityNo;
}
