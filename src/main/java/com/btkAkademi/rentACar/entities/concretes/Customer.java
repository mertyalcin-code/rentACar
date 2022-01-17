package com.btkAkademi.rentACar.entities.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Rental> rentals;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<CustomerCardDetail> customerPaymentDetails;
}
