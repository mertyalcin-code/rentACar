package com.btkAkademi.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "rent_date")
	private LocalDate rentDate;
	@Column(name = "return_date")
	private LocalDate returnDate;
	@Column(name = "rented_kilometer")
	private Integer rentedKilometer;
	@Column(name = "returned_kilometer")
	private Integer returnedKilometer;

	@ManyToOne
	@JoinColumn(name = "pick_up_city_id")
	private City pickUpCity;

	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "promo_code_id")
	private PromoCode promoCode;

	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> additionalService;

	@OneToMany(mappedBy = "rental")
	private List<Payment> payments;

	@OneToOne(mappedBy = "rental")
	private Invoice invoice;

}
