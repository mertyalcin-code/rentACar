package com.btkAkademi.rentACar.business.requests.paymentRequest;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import com.btkAkademi.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	private LocalDateTime paymentTime;
	private int rentalId;
}
