package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCardDetailListDto {
	private int id;
	private String cardNo;
	private String year;
	private String month;
	private String cvv;
	private int customerId;
}
