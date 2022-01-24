package com.btkAkademi.rentACar.core.utilities.loaders;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.City;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CityDataLoader implements CommandLineRunner {
	private CityDao cityDao;

	@Override
	public void run(String... args) throws Exception {
		loadCities();

	}

	private void loadCities() {
		if (cityDao.count() == 0) {
			HashSet<City> cities = new HashSet<>();
			cities.add(new City(0, "Ankara", null, null, null));
			cities.add(new City(0, "İzmir", null, null, null));
			cities.add(new City(0, "İstanbul", null, null, null));
			cities.add(new City(0, "Antalya", null, null, null));
			cities.add(new City(0, "Konya", null, null, null));
			cityDao.saveAll(cities);
		}

	}
}
