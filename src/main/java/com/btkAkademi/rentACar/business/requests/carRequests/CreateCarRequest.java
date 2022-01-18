package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private int brandId;
	private int colorId;
	private double dailyPrice;
	private int model;
	private int findexScore;
	private int kilometer;
	@NotBlank
	private String description;
	private int minAge;
	private int segmentId;
}
