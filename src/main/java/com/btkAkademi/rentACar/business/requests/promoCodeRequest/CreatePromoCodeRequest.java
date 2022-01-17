package com.btkAkademi.rentACar.business.requests.promoCodeRequest;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePromoCodeRequest {	
	private String code ;		
	private double discountRate;
	private LocalDate startDate;	
	private LocalDate endDate;
	private String description;
	
}
