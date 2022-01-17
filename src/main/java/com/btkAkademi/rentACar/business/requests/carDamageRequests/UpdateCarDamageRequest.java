package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarDamageRequest {
	private int id;
	@NotBlank
	@Size(min = 0, max = 255)
	private String description;
	private int carId;

}
