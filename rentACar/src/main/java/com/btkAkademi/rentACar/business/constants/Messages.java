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
	//Color
	public static final String invalidColorName ="invalid.color.name";
	public static final String colorAdded = "color.added";
	public static final String colorNameExists = "color.name.exists";
	public static final String colorUpdate = "color.updated";
	public static final String colorIdNotExists = "color.id.not.exists";
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
	public static final String carMaintananceAdded = "car.maintanance.added";
	public static final String carInMaintanance = "car.in.maintanance";
	public static final String carDamageAdded = "car.damage.added";
	public static final String carRented = "car.rented";
	public static final String cityNameExists = "city.name.exists";
	
	
	
}
