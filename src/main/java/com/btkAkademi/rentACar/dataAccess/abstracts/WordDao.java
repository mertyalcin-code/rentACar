package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Word;

public interface WordDao extends JpaRepository<Word, Integer> {
	Word getWordsByKey(String key);

	boolean existsByKey(String key);
}