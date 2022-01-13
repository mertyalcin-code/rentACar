package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer> {
	City findByCityName(String CityName);
}
