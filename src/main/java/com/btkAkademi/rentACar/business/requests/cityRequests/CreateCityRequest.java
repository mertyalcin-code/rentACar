package com.btkAkademi.rentACar.business.requests.cityRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCityRequest {
	@NotBlank
	private String cityName;
}
