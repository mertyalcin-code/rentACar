package com.btkAkademi.rentACar.business.requests.cityRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCityRequest {
	private int id;
	@NotBlank
	private String cityName;
}
