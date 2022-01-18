package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Language;

public interface LanguageDao extends JpaRepository<Language, Integer> {

	Language getLanguagesByName(String languageName);
}