package com.btkAkademi.rentACar.entities.concretes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="cars")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="daily_price")
	private double dailyPrice;
	@Column(name="model")
	private int model;
	@Column(name="description")
	private String description;
	@Column(name="findex_score")
	private int findexScore;
	@Column(name="kilometer")
	private int kilometer;	

	
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car")
	@JsonIgnore
	private List<CarMaintanance> maintanances;
	
	@JsonIgnore
	@OneToMany(mappedBy = "car")
	private List<Rental> rentals;

}
