package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceService;
import com.btkAkademi.rentACar.business.abstracts.RentalService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.CreateAdditionalServiceRequest;
import com.btkAkademi.rentACar.business.requests.additionalServiceRequests.UpdateAdditionalServiceRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	// Dependencies
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	// Dependency injection
	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService,
			@Lazy RentalService rentalService) {
		super();
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	// list all additional services for one rental
	@Override
	public DataResult<List<AdditionalServiceListDto>> findAllByRentalId(int RentalId) {
		List<AdditionalService> additionalServiceList = this.additionalServiceDao.findAllByRentalId(RentalId);
		List<AdditionalServiceListDto> response = additionalServiceList.stream().map(
				additionalService -> modelMapperService.forDto().map(additionalService, AdditionalServiceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<AdditionalServiceListDto>>(response,Messages.LIST);
	}

	// Adds a rental to additional service
	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalService) {
		Result result = BusinessRules.run(checkIfRentalExists(createAdditionalService.getRentalId()));
		if (result != null) {
			return result;
		}

		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalService,
				AdditionalService.class);
		// avoid error
		additionalService.setId(0);
		this.additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONALSERVICEADD);
	}
	@Override
	public Result addAll(List<CreateAdditionalServiceRequest> createAdditionalServiceRequests) {
		Result result = BusinessRules.run(checkIfRentalExists(createAdditionalServiceRequests.get(0).getRentalId()));
		if (result != null) {
			return result;
		}
	
		List<AdditionalService> response = createAdditionalServiceRequests.stream().map(
				service -> modelMapperService.forRequest().map(service, AdditionalService.class)				
				)
				.collect(Collectors.toList());
		for(AdditionalService service: response) {
			service.setId(0);
		}
		this.additionalServiceDao.saveAll(response);
		return new SuccessResult(Messages.ADDITIONALSERVICEADD);
	}

	// Updates additional service
	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		Result result = BusinessRules.run(checkIfRentalExists(updateAdditionalServiceRequest.getRentalId()));

		if (result != null) {
			return result;
		}
		AdditionalService additionalService = modelMapperService.forRequest().map(updateAdditionalServiceRequest,
				AdditionalService.class);
		additionalServiceDao.save(additionalService);
		return new SuccessResult(Messages.ADDITIONALSERVICEUPDATE);
	}

	// delete
	@Override
	public Result delete(int id) {
		if (additionalServiceDao.existsById(id)) {
			additionalServiceDao.deleteById(id);
			return new SuccessResult(Messages.ADDITIONALSERVICEDELETE);
		} else
			return new ErrorResult(Messages.ADDITIONALSERVICENOTFOUND);
	}

	// Checks if rental is exists
	private Result checkIfRentalExists(int rentalId) {
		if (!rentalService.findById(rentalId).isSuccess()) {
			return new ErrorResult(Messages.RENTALNOTFOUND);
		} else
			return new SuccessResult();
	}



}
