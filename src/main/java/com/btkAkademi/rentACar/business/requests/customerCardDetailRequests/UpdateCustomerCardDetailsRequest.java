package com.btkAkademi.rentACar.business.requests.customerCardDetailRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerCardDetailsRequest {
	private int id;
	@NotBlank
	@Size(min = 16, max = 16)
	@Pattern(regexp = "[0-9]+",message = Messages.CREDITCARDNUMBERERROR)
	private String cardNo;
	@NotBlank
	@Size(min = 2, max = 2)
	@Pattern(regexp = "([2][2-9])|([3][0-9])",message = Messages.CREDITCARDDATEERROR)
	private String year;
	@NotBlank
	@Size(min = 2, max = 2)
	@Pattern(regexp = "[0][1-9]|[1][0-2]",message = Messages.CREDITCARDDATEERROR)
	private String month;
	@NotBlank
	@Size(min = 3, max = 3)
	@Pattern(regexp = "[0-9][0-9][0-9]",message = Messages.CREDITCARDCVVERROR)
	private String cvv;
	private int customerId;
}
