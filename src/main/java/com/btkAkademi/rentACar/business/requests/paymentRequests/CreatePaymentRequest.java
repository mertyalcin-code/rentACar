package com.btkAkademi.rentACar.business.requests.paymentRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	private LocalDate paymentTime;
	private int rentalId;
	private String cardNo;
	private String day;
	private String month;
	private String cvv;
}
