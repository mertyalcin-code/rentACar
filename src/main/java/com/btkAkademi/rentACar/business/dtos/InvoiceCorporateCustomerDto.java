package com.btkAkademi.rentACar.business.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceCorporateCustomerDto {
	private int id;
	private String taxNumber;
	private String companyName;
	private String email;
	private LocalDate rentDate;
	private LocalDate returnedDate;
	private double totalPrice;
	private LocalDate creationDate;
	private List<AdditionalServiceItemListDto> additonalServiceItems;

}
