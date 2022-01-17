package com.btkAkademi.rentACar.business.dtos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto{
	
	private int id;
	private double dailyPrice;	
	private int model;
	private String description;
	private int findexScore;	
	private int kilometer;	
	private String brandName;	
	private String colorName;
	private int minAge;
}
