package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer> {
	// Finds Corporate Customer by Email
	CorporateCustomer findByEmail(String email);

	// Finds Corporate Customer by companyName
	CorporateCustomer findByCompanyName(String companyName);
}
