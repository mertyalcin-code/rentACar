package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CorporateCustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CorporateCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequests.UpdateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
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
	@Override
	public DataResult<List<CorporateCustomerListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(pageable).getContent();
		List<CorporateCustomerListDto> response = corporateCustomers.stream().map(
				corporateCustomer -> modelMapperService.forDto().map(corporateCustomer, CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
	}

	// finds by id
	@Override
	public DataResult<CorporateCustomerListDto> findById(int id) {
		if (corporateCustomerDao.existsById(id)) {
			CorporateCustomer corporateCustomer = corporateCustomerDao.findById(id).get();
			CorporateCustomerListDto response = modelMapperService.forDto().map(corporateCustomer,
					CorporateCustomerListDto.class);
			return new SuccessDataResult<>(response);
		} else
			return new ErrorDataResult<CorporateCustomerListDto>(Messages.notFound);
	}

	// Adds a new corporate customer
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		Result result = BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()),
				checkIfEmailExists(createCorporateCustomerRequest.getEmail()));

		if (result != null) {
			return result;
		}

		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerAdded);
	}

	// Updates a customer but does not control email or name exists or not
	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest,
				CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.corporateCustomerUpdated);
	}

	// delete
	@Override
	public Result delete(int id) {
		if (corporateCustomerDao.existsById(id)) {
			corporateCustomerDao.deleteById(id);
			return new SuccessResult(Messages.corporateCustomerDeleted);
		}
		return new ErrorResult(Messages.notFound);
	}

	// Helpers

	// Checks company name exists in the database
	private Result checkIfCompanyNameExists(String companyName) {
		if (corporateCustomerDao.findByCompanyName(companyName) != null) {
			return new ErrorResult(Messages.companyNameExists);
		}
		return new SuccessResult();

	}

	// checks there is a registered user with that email
	private Result checkIfEmailExists(String email) {
		if (corporateCustomerDao.findByEmail(email) != null) {
			return new ErrorResult(Messages.emailExist);
		}
		return new SuccessResult();
	}

}
