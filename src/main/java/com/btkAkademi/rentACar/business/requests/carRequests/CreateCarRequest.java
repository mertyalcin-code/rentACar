package com.btkAkademi.rentACar.business.requests.carRequests;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Color;

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
}
