package com.btkAkademi.rentACar.business.dtos;

import java.util.List;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServiceItemListDto {
	private int id;
	private String name;
	private double price;
}
