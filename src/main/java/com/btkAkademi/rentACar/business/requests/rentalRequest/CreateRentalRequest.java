package com.btkAkademi.rentACar.business.requests.rentalRequest;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	private LocalDate rentDate;
	private int rentedKilometer;
	private int pickUpCityId;
	private int returnCityId;
	private int customerId;	
	private int carId;
}
