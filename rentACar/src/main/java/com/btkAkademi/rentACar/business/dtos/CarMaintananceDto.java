package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;

public class CarMaintananceDto {
	private int id;
	private Car car ;		
	private LocalDate enteranceDate;
	private LocalDate returnDate;
}
