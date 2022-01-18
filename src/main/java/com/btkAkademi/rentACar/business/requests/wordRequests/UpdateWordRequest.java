package com.btkAkademi.rentACar.business.requests.wordRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWordRequest {

	private int id;
	private String key;
}