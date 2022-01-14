package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPaymentDetailListDto {
	private int id;
	private String cardNo;
	private String day;
	private String month;
	private String cVV;
}
