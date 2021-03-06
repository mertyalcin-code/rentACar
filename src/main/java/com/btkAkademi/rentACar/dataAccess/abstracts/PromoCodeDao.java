package com.btkAkademi.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.PromoCode;

public interface PromoCodeDao extends JpaRepository<PromoCode, Integer> {

	PromoCode findByCode(String code);

	/*
	 * @Query("select p from PromoCode p where p.EndDate <= :today") List<PromoCode>
	 * findAllActiveCodes(@Param("today") LocalDate today);
	 */
}
