package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Car;



public interface CarDao  extends JpaRepository<Car, Integer>{
	
	List<Car> findAll(Pageable pageable);

}
