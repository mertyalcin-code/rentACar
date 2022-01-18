package com.btkAkademi.rentACar.business.requests.invoiceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
	private int id;
	private int rentalId;
	private LocalDate creationDate;
}
