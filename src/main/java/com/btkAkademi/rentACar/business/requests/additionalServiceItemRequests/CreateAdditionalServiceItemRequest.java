package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceItemRequest {
	 @NotBlank
	 @Size(min = 2,max = 250)
	private String name;
	private double price;
}
