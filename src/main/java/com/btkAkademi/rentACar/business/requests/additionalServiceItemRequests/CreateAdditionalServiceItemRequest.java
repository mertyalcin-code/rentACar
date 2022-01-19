package com.btkAkademi.rentACar.business.requests.additionalServiceItemRequests;

import javax.validation.constraints.Min;
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
	 @Min(0)
	private double price;
}
