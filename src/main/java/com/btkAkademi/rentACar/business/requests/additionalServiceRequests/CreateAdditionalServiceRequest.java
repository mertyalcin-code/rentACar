package com.btkAkademi.rentACar.business.requests.additionalServiceRequests;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceRequest {	
	private int rentalId;
	private int additionalServiceItemId;
}
