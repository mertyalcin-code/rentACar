package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	// Finds car if it is rented
	Rental findByCarIdAndReturnDateIsNull(int carId);

	List<Rental> findAllByCustomerId(int customerId);

}
