package com.btkAkademi.rentACar.business.requests.wordRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWordRequest {

	@JsonIgnore
	private int id;

	private String key;
}