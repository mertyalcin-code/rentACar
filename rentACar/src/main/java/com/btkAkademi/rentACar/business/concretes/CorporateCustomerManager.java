package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	// Dependencies
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	// Dependency Injection
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}
	// Lists all corporate customer
	
	
	// Adds a new corporate customer
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result = BusinessRules.run(
				checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()));
		
		if(result!=null) {			
			return result;
		}
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerAdded);
	}
	
	
	//Helpers
	
	

	// Checks company name exists in the database
	private Result checkIfCompanyNameExists(String companyName) {
		if(corporateCustomerDao.findByCompanyName(companyName)!=null ) {
			return new ErrorResult(Messages.companyNameExists);
		}
		return new SuccessResult();
	
	}




	
}
