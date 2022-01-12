package com.btkAkademi.rentACar.business.concretes;


import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequest.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.Color;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;
@Service
public class IndividualCustomerManager implements IndividualCustomerService{
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	

	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao,
			ModelMapperService modelMapperService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		Result result = BusinessRules.run(
				checkIfEmailExists(createIndividualCustomerRequest.getEmail()),
				checkIfUserInAgeLimit(createIndividualCustomerRequest.getBirthDate())
				);
		
		if(result!=null) {			
			return result;
		}
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest,IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.individualCustomerAdded);
	}
	
	
	private Result checkIfEmailExists(String email) {
		if(individualCustomerDao.findByEmail(email)!=null) {
			return new ErrorResult(Messages.emailExist);
		}
		return new SuccessResult();
	}
	private Result checkIfUserInAgeLimit(LocalDate birthDate) {
		int Age = Period.between(birthDate, LocalDate.now()).getYears();
		if(Age<18 ) {
			return new ErrorResult(Messages.ageNotInLimit);
		}
		return new SuccessResult();
	}
	
}
