package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.AdditionalServiceItem;

public interface AdditionalServiceItemDao extends JpaRepository<AdditionalServiceItem, Integer> {

}
