package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {
	private int id;
	private String carName;
	private double dailyPrice;
	private int model;
	private String description;
	private int findexScore;
	private int kilometer;
	private String brandName;
	private String colorName;
	private String imageUrl;
	private int minAge;
	private int segmentId;
	private int cityId;
	private String CityName;
	private String SegmentName;
	private String brandId;
	private int colorId;
}
