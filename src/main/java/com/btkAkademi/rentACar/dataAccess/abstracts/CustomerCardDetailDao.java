package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CustomerCardDetail;

public interface CustomerCardDetailDao extends JpaRepository<CustomerCardDetail, Integer> {

	List<CustomerCardDetail> findAllByCustomerId(int customerId);
}
