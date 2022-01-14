package com.btkAkademi.rentACar.business.constants;

import com.btkAkademi.rentACar.entities.concretes.Customer;

public class Messages {
	//Brand
	public static final String invalidBrandName="invalid.brand.name";
	public static final String brandAdded = "brand.added";
	public static final String brandNameExists = "brand.name.exists";
	public static final String brandLimitExceeded= "brand.limit.exceeded";
	public static final String brandUpdated = "brand.updated";
	public static final String brandIdNotExists = "brand.id.not.exists";
	public static final String brandNotFound = "brand.not.found";
	//Color
	public static final String invalidColorName ="invalid.color.name";
	public static final String colorAdded = "color.added";
	public static final String colorNameExists = "color.name.exists";
	public static final String colorUpdate = "color.updated";
	public static final String colorIdNotExists = "color.id.not.exists";
	public static final String colorNotFound = "color.not.found";
	//Car
	public static final String carAdded = "car.added";
	public static final String carUpdated = "car.updated";
	public static final String carIdNotExists = "car.id.not.exists";
	//User
	public static final String emailExist = "email.exists";
	public static final String ageNotInLimit = "age.not.in.limit";
	public static final String individualCustomerAdded = "individual.customer.added";
	public static final String corporateCustomerAdded = "corporate.customer.added";
	public static final String companyNameExists = "company.name.exists";
	public static final String customerNotFound = "customer.not.found";
	//Rental
	public static final String returnDateShouldBeAfterTheRentDate = "return.date.should.be.after.the.return.date";
	public static final String rentalAdded = "rental.added";
	public static final String returnedKilometerShouldNotBeLowerThanRentedKilometer = "returned.kilometer.should.not.be.lower.than.rented.kilometer";
	public static final String carRented = "car.rented";	
	//Maintenance
	public static final String carMaintananceAdded = "car.maintanance.added";
	public static final String carInMaintanance = "car.in.maintanance";	
	//Car Damage
	public static final String carDamageAdded = "car.damage.added";
	//City
	public static final String cityNameExists = "city.name.exists";
	//Additional Service
	public static final String additionalServiceAdded = "additional.service.added";
	public static final String rentalIsNotFound = "rental.is.not.found";
	public static final String limitNotEnough = "limit.not.enough";
	public static final String carSaved = "card.saved";
	public static final String additionalServiceUpdated = "additionalServiceUpdated";
	public static final String carDamageUpdated = "car.damage.updated";
	public static final String carMaintananceUpdated = "car.maintanance.updated";
	public static final String carMaintananceDeleted = "car.maintanance.deleted";
	public static final String carDeleted = "car.deleted";
	public static final String cityAdded = "city.added";
	public static final String cityUpdated = "city.updated";
	public static final String cityDeleted = "city.deleted";
	public static final String colorDeleted = "color.deleted";
	public static final String corporateCustomerUpdated = "corporate.customer.updated";
	public static final String corporateCustomerDeleted = "corporate.customer.added";
	public static final String customerPaymentDetailAdded = "customer.payment.detail.added";
	public static final String customerPaymentDetailUpdated = "customer.payment.detail.updated";
	public static final String customerPaymentDetailDeleted = "customer.payment.detail.deleted";
	public static final String individualCustomerUpdated = "individual.customer.updated";
	public static final String individualCustomerDeleted= "individual.customer.deleted";
	public static final String paymentDeleted = "payment.deleted";
	public static final String rentalUpdated = "rental.updated";
	public static final String rentalDeleted = "rental.deleted";
	public static final String brandDeleted = "brand.deleted";
	public static final String paymentAdded = "payment.added";
	public static final String paymentUpdated = "payment.updated";
	
	
}
