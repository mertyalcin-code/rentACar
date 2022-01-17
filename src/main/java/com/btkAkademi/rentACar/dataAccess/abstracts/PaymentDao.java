package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

	List<Payment> getAllByRentalId(int rentalId);
}
