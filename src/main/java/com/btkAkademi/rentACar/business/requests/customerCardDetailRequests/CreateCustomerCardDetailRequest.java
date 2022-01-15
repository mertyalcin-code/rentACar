package com.btkAkademi.rentACar.business.requests.customerCardDetailRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCardDetailRequest {
	private String cardNo;
	private String day;
	private String month;
	private String cvv;
	private int customerId;
}
