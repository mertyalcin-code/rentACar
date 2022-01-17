package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarDamage;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer> {

	List<CarDamage> findAllByCarId(int id);

}
