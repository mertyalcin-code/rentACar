package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CustomerPaymentDetail;

public interface CustomerPaymentDetailDao extends JpaRepository<CustomerPaymentDetail, Integer>{
	
	List<CustomerPaymentDetail> findAllByCustomerId(int customerId);
}
