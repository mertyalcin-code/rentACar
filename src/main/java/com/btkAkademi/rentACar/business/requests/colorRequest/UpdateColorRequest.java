package com.btkAkademi.rentACar.business.requests.colorRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.btkAkademi.rentACar.business.constants.Messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {

	private int id;
	@NotBlank
	@Size( message = Messages.invalidColorName)
	private String name;
}
