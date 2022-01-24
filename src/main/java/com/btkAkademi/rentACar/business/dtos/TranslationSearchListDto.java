package com.btkAkademi.rentACar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationSearchListDto {

	private int id;
	private String translation;
	private int wordId;
	private String wordKey;
	private int languageId;
}