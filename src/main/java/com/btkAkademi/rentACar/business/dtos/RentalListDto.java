package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalListDto {
	private int id;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private Integer rentedKilometer;
	private Integer returnedKilometer;
	private int customerId;
	private int carId;
	private int promoCodeId;
}
