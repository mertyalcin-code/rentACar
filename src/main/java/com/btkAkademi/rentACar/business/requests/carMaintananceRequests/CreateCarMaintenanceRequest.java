package com.btkAkademi.rentACar.business.requests.carMaintananceRequests;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	private int carId;
	
	private LocalDate maintenanceStart;

}
