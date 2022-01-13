package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.additionalService;

public interface AdditionalServiceDao extends JpaRepository<additionalService, Integer>{

}
