package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CarDamage;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer>{

}
