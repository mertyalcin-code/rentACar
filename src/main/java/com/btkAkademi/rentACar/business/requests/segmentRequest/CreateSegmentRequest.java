package com.btkAkademi.rentACar.business.requests.segmentRequest;

import java.time.LocalDate;

import com.btkAkademi.rentACar.business.requests.rentalRequests.CreateRentalRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSegmentRequest {
	private String segmentName;
	
}
