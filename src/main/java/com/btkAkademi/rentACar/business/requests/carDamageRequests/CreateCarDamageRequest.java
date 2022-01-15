package com.btkAkademi.rentACar.business.requests.carDamageRequests;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDamageRequest {
	@NotBlank
	@Size(min=0,max=255)
	private String description;	
	private int carId ;

}
