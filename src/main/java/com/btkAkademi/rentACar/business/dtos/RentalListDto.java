package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalListDto {
	private int id ;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private int rentedKilometer;
	private int returnedKilometer;
	private int customerId;
	private int carId;
}
