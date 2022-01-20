package com.btkAkademi.rentACar.business.dtos;

import com.btkAkademi.rentACar.entities.concretes.Language;
import com.btkAkademi.rentACar.entities.concretes.Word;

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