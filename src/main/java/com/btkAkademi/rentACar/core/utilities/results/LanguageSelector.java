package com.btkAkademi.rentACar.core.utilities.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.LanguageService;
import com.btkAkademi.rentACar.business.abstracts.TranslationService;
import com.btkAkademi.rentACar.business.abstracts.WordService;
import com.btkAkademi.rentACar.business.dtos.LanguageSearchListDto;
import com.btkAkademi.rentACar.business.dtos.TranslationSearchListDto;
import com.btkAkademi.rentACar.business.dtos.WordSearchListDto;
import com.btkAkademi.rentACar.core.constants.Languages;
import com.btkAkademi.rentACar.entities.concretes.Translation;
import com.btkAkademi.rentACar.entities.concretes.Word;

import lombok.var;

@Service
public class LanguageSelector {

	private static LanguageService languageService;
	private static WordService wordService;
	private static TranslationService translationService;
	private static Environment environment;

	@Autowired
	public LanguageSelector(LanguageService languageService, WordService wordService,
			TranslationService translationService, Environment environment) {
		LanguageSelector.languageService = languageService;
		LanguageSelector.wordService = wordService;
		LanguageSelector.translationService = translationService;
		LanguageSelector.environment = environment;
	}

	public static String languageSelector(String message) {
		String result = environment.getProperty("current_language", Languages.ENGLISH);

		LanguageSearchListDto language = languageService.getByLanguageName(result).getData();
		if (language == null) {
			language.setName(Languages.ENGLISH);
		}
		if(!wordService.getByKey(message).isSuccess()) {
			return "No Message Available";
		}
		WordSearchListDto word = wordService.getByKey(message).getData();		
	
		Translation translation = translationService.getTranslationByLanguage_IdAndWord_Id(language.getId(),
					word.getId());
			return translation==null ? "No Message Available" : translation.getTranslation();
		
	
	}

}