package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.IndividualCustomerService;
import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.constants.Role;
import com.btkAkademi.rentACar.business.dtos.IndividualCustomerListDto;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.CreateIndividualCustomerRequest;
import com.btkAkademi.rentACar.business.requests.individualCustomerRequests.UpdateIndividualCustomerRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.IndividualCustomerDao;
import com.btkAkademi.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	// Constants
	private static final int ageLimit = 18;

	// Dependencies
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	private UserService userService;

	// Dependency Injection
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService,
			UserService userService) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
		this.userService = userService;
	}

	// Lists all individual customers according to page
	@Override
	public DataResult<List<IndividualCustomerListDto>> findAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll(pageable).getContent();
		List<IndividualCustomerListDto> response = individualCustomers.stream()
				.map(individualCustomer -> modelMapperService.forDto().map(individualCustomer,
						IndividualCustomerListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<IndividualCustomerListDto>>(response, Messages.CUSTOMERLIST);
	}

	// find by id
	@Override
	public DataResult<IndividualCustomerListDto> findById(int id) {
		Result result = BusinessRules.run(checkIfCustomerIdExists(id));
		if (result != null) {
			return new ErrorDataResult<IndividualCustomerListDto>(result.getMessage());
		}
		IndividualCustomer individualCustomer = individualCustomerDao.findById(id).get();
		IndividualCustomerListDto response = modelMapperService.forDto().map(individualCustomer,
				IndividualCustomerListDto.class);
		return new SuccessDataResult<IndividualCustomerListDto>(response, Messages.CUSTOMERLIST);

	}

	// Adds a new individual customer
	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		Result result = BusinessRules.run(checkIfEmailExists(createIndividualCustomerRequest.getEmail()),
				checkIfUserInAgeLimit(createIndividualCustomerRequest.getBirthDate()));
		if (result != null) {
			return result;
		}

		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(createIndividualCustomerRequest, IndividualCustomer.class);
		individualCustomer.setRole(Role.INDIVIDUAL_CUSTOMER.getRole());
		this.individualCustomerDao.save(individualCustomer);

		return new SuccessResult(Messages.CUSTOMERREGISTRATIONSUCCESSFUL);
	}

	// Update a customer
	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		Result result = BusinessRules.run(checkIfUserInAgeLimit(updateIndividualCustomerRequest.getBirthDate()));
		if (result != null) {
			return result;
		}

		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(updateIndividualCustomerRequest, IndividualCustomer.class);
		// avoid null password
		individualCustomer.setPassword(individualCustomerDao.findById(individualCustomer.getId()).get().getPassword());
		individualCustomer.setRole(Role.INDIVIDUAL_CUSTOMER.getRole());
		this.individualCustomerDao.save(individualCustomer);

		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	// Delete a customer
	@Override
	public Result delete(int id) {
		Result result = BusinessRules.run(checkIfCustomerIdExists(id));
		if (result != null) {
			return result;
		}
		individualCustomerDao.deleteById(id);
		return new SuccessResult();

	}

	// Helpers

	// Checks anyone registered with that email before
	private Result checkIfEmailExists(String email) {
		System.out.println(userService.findByEmail(email).getData());
		if (userService.findByEmail(email).getData() != null) {
			return new ErrorResult(Messages.EMAILERROR);
		}
		return new SuccessResult();
	}

	// Checks the age limitation
	private Result checkIfUserInAgeLimit(LocalDate birthDate) {
		int Age = Period.between(birthDate, LocalDate.now()).getYears();
		if (Age < ageLimit) {
			return new ErrorResult(Messages.AGENOTENOUGH);
		}
		return new SuccessResult();
	}

	// Checks is there a customer with that id
	private Result checkIfCustomerIdExists(int id) {
		if (!this.individualCustomerDao.existsById(id)) {
			return new ErrorResult(Messages.CUSTOMERNOTFOUND);
		}
		return new SuccessResult();
	}

}
