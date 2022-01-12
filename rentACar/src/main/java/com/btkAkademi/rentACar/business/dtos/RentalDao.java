package com.btkAkademi.rentACar.business.dtos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer>{
	
	
}
