package com.btkAkademi.rentACar.business.concretes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.requests.additionalService.createAdditionalService;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;
@Service
public class AdditionalServiceManager implements AdditionalServiceService{
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	
	
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(createAdditionalService createAdditionalService) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalService, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.additionaServiceAdded);
	}

}
