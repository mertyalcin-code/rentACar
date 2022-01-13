package com.btkAkademi.rentACar.business.requests.additionalService;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceRequest {
	@NotBlank
	private String name;
	private int rentalId;
}
