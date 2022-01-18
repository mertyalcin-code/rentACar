package com.btkAkademi.rentACar.business.requests.cityRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCityRequest {
	@NotBlank
	@Size(max=250)
	private String cityName;
}
