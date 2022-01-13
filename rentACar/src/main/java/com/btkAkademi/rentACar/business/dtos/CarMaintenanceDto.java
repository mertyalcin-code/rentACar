package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;

public class CarMaintenanceDto {
	private int id;
	private int carId ;		
	private LocalDate maintenanceStart;
	private LocalDate maintenanceEnd;
}
