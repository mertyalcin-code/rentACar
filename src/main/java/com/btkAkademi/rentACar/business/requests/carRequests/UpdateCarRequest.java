package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	private String carName;
	private int id;
	private int brandId;
	private int colorId;
	@NotNull
	@Min(0)
	private double dailyPrice;
	
	@NotNull
	@Min(1900)	
	private int model;
	
	@NotNull
	@Min(650)
	@Max(1900)
	private int findexScore;
	
	@NotNull
	@Min(0)
	@Max(2500000)
	private int kilometer;
	
	private String imageUrl;
	@NotBlank
	private String description;
	@Min(18)
	private int minAge;	
	private int segmentId;
	private int cityId;
}