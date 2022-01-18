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
public class UpdateIndividualCustomerRequest {

	private int id;
	@Size(min = 11, max = 11)
	private String nationalityNo;
	@Email
	@NotBlank
	private String email;
	@Size(min = 2, max = 100)
	private String firstName;
	@Size(min = 2, max = 100)
	private String lastName;
	@NotNull
	private LocalDate birthDate;
}
