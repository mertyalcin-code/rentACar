package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyRentalListDto {
	private int rentalId;
	private String brandName;
	private String carName;
	private LocalDate RentDate;
	private LocalDate returnDate;
	private String pickUpCityName;
	private String returnCityName;
	private double totalPayment;
	private boolean isInvoiceCreated;
	private boolean isRentalFinished;
}
