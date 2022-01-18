package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Translation;

public interface TranslationDao extends JpaRepository<Translation, Integer> {
	Translation getTranslationByLanguage_IdAndWord_Id(int languageId, int wordId);
}