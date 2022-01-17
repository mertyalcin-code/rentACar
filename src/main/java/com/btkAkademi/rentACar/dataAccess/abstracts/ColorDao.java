package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Color;

public interface ColorDao extends JpaRepository<Color, Integer> {
	// finds Color with color name
	Color findByName(String colorName);

}
