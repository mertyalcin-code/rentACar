package com.btkAkademi.rentACar.business.requests.rentalRequests;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private LocalDate rentDate;
	private LocalDate returnDate;
	private int customerId;
	private int carId;
	private int promoCodeId;

}
