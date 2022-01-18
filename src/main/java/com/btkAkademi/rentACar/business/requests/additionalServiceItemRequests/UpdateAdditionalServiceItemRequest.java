package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServiceItemRequest {
	private String name;
	private double price;
}
