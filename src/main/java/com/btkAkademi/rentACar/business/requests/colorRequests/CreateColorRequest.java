package com.btkAkademi.rentACar.business.requests.colorRequests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {	
	@NotBlank
	@Size( message = Messages.invalidColorName)
	private String name;
}
