package com.btkAkademi.rentACar.business.requests.invoiceRequest;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;

import lombok.Data;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	private int rentalId;
	private LocalDate creationDate;
}
