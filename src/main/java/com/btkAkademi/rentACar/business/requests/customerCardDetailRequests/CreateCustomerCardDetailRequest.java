package com.btkAkademi.rentACar.business.requests.customerCardDetailRequests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCardDetailRequest {
	@NotEmpty
	private String cardNo;
	@NotEmpty
	private String day;
	@NotEmpty
	private String month;
	@NotEmpty
	private String cvv;
	private int customerId;
}
