package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoCodeDto {
	private int id;
	private String code;
	private double discountRate;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;
}
