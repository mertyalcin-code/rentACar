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
public class createRentalRequest {

	private LocalDate rentDate;

	private LocalDate returnDate;

	private int rentedKilometer;

	private int returnedKilometer;

	private Customer customer;
	
	private Car car;
}
