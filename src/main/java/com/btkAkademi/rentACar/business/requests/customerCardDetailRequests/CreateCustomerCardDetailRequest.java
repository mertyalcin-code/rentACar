package com.btkAkademi.rentACar.business.requests.customerCardDetailRequests;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerCardDetailRequest {
	@NotBlank
	@Size(min=16,max=16)
	@Pattern(regexp = "[0-9]+")
	private String cardNo;
	@NotBlank
	@Size(min=2,max=2)
	@Pattern(regexp = "[0-9]+")
	private String year;
	@NotBlank
	@Size(min=2,max=2)
	@Pattern(regexp = "[0-9]+")
	private String month;
	@NotBlank
	@Size(min=3,max=3)
	@Pattern(regexp = "[0-9]+")
	private String cvv;
	private int customerId;
}
