package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarMaintanance;

public interface CarMaintananceDao extends JpaRepository<CarMaintanance, Integer> {
		
	List<CarMaintanance> findAllByCarId(int carId);
	
	CarMaintanance findTop1ByOrderByReturnDateDesc(int CarId);
}
