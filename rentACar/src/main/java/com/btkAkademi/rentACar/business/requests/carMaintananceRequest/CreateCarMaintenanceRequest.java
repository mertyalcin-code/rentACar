package com.btkAkademi.rentACar.business.requests.carMaintananceRequest;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {
	
	private int carId ;		
	private LocalDate maintananceStart;
	private LocalDate maintenanceEnd;
}
