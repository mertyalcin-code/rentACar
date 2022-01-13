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
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.corporateCustomerRequest.CreateCorporateCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CorporateCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.CorporateCustomer;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

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
	public DataResult<List<CorporateCustomerListDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll(pageable).getContent();
		List<CorporateCustomerListDto> response = corporateCustomers.stream()
				.map(corporateCustomer -> modelMapperService.forDto().map(corporateCustomer,
						CorporateCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response);
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

	// Helpers

	// Checks company name exists in the database
	private Result checkIfCompanyNameExists(String companyName) {
		if (corporateCustomerDao.findByCompanyName(companyName) != null) {
			return new ErrorResult(Messages.companyNameExists);
		}
		return new SuccessResult();

	}
	private Result checkIfEmailExists(String email) {
		if (corporateCustomerDao.findByEmail(email) != null) {
			return new ErrorResult(Messages.emailExist);
		}
		return new SuccessResult();
	}

}
