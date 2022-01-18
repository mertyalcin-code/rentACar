package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> findAllByBrandId(int brandId, Pageable pagable);

	List<Car> findAllByColorId(int colorId, Pageable pagable);
	
	List<Car> findAllBySegmentId(int segmentId);

}
