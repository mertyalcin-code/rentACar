package com.btkAkademi.rentACar.business.requests.translationRequests;

import com.btkAkademi.rentACar.entities.concretes.Language;
import com.btkAkademi.rentACar.entities.concretes.Word;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTranslationRequest {

	@JsonIgnore
	private int id;

	private String translation;

	private Word word;

	private Language language;
}