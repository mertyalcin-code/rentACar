package com.btkAkademi.rentACar.business.requests.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class createAdditionalService {
	private String name;
	private int rentalId;
}
