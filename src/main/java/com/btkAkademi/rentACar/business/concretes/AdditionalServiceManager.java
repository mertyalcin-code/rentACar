package com.btkAkademi.rentACar.business.concretes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	//Dependencies
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	//Dependency injection
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao,
			ModelMapperService modelMapperService,@Lazy RentalService rentalService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}
		
	//Adds a rental to additional service
	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalService) {
		Result result = BusinessRules.run(
				checkIfRentalIsExists(createAdditionalService.getRentalId()));
		
		if(result!=null) {
			
			return result;
		}
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalService, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionaServiceAdded);
	}
	// Checks if car is exists
		private Result checkIfRentalIsExists(int rentalId) {
			if (!rentalService.findById(rentalId).isSuccess()) {
				return new ErrorResult(Messages.rentalIsNotFound);
			} else
				return new SuccessResult();
		}

	
}
	