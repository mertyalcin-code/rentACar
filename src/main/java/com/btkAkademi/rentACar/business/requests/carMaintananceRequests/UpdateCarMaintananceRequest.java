package com.btkAkademi.rentACar.business.requests.carMaintananceRequests;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintananceRequest {
	private int id;
	private int carId;
	private LocalDate maintenanceStart;
	private LocalDate maintenanceEnd;
}
