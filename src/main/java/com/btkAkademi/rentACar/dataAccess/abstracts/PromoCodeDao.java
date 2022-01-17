package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.btkAkademi.rentACar.entities.concretes.PromoCode;

public interface PromoCodeDao extends JpaRepository<PromoCode, Integer> {

	PromoCode findByCode(String code);

	/*
	@Query("select p from PromoCode p where p.EndDate <= :today")
	List<PromoCode> findAllActiveCodes(@Param("today") LocalDate today);
	*/
}
