package com.btkAkademi.rentACar.business.requests.carRequest;

import javax.persistence.Column;

import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	private Brand brand;
	private Color color;
	private double dailyPrice;
	private int model;
	private int findexScore;
	private int kilometer;
	private String description;
}
