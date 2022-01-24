package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateTotalPriceRequest {
	private int rentalId;
	private LocalDate returnDate;

}
