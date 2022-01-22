package com.btkAkademi.rentACar.core.utilities.results;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.LanguageService;
import com.btkAkademi.rentACar.business.abstracts.TranslationService;
import com.btkAkademi.rentACar.business.abstracts.WordService;
import com.btkAkademi.rentACar.core.constants.Languages;

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

		var language = languageService.getByLanguageName(result);
		if (language == null) {
			language.getData().setName(Languages.ENGLISH);
		}
		var word = wordService.getByKey(message);
		var translation = translationService.getTranslationByLanguage_IdAndWord_Id(language.getData().getId(),
				word.getData().getId());
		return translation.getTranslation()==null ? "" :translation.getTranslation();
	}

}