package com.btkAkademi.rentACar.business.requests.carDamageRequest;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.btkAkademi.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDamageRequest {

	private String description;	
	private int carId ;

}
