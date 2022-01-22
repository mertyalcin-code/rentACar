package com.btkAkademi.rentACar.core.utilities.loaders;

import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.btkAkademi.rentACar.dataAccess.abstracts.CarDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.CityDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.City;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class CarDataLoader implements CommandLineRunner{
	private CarDao carDao;
	
	@Override
	public void run(String... args) throws Exception {
	//	loadCars();

	}

	private void loadCars() {
		if(carDao.count()==0) {
			HashSet<Car> cars = new HashSet<>();
			//cars.add();
		
			carDao.saveAll(cars);
		}
		
	}

}
