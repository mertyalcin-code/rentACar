package com.btkAkademi.rentACar.business.requests.individualCustomerRequests;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.requests.colorRequests.CreateColorRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	@Size(min=11,max=11)
	private String nationalityId;
	@Email
	@NotBlank
	private String email;
	@Size(min=4,max=30)
	private String password;
	@Size(min=2,max=100)
	private String firstName;
	@Size(min=2,max=100)
	private String lastName;	
	@NotNull
	private LocalDate birthDate;
}
