package com.btkAkademi.rentACar.business.requests.carMaintananceRequest;

import java.time.LocalDate;

import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.CarMaintanance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintananceRequest {
	
	private Car car ;		
	private LocalDate enteranceDate;
	private LocalDate returnDate;
}
