package com.btkAkademi.rentACar.business.requests.individualCustomerRequest;

import java.time.LocalDate;

import javax.persistence.Column;

import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
}
