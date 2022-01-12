package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.CustomerService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.CustomerDao;
import com.btkAkademi.rentACar.entities.concretes.Customer;

@Service

public class CustomerManager implements CustomerService {
	private CustomerDao customerDao;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<Customer> findCustomerById(int id) {
		if(!customerDao.existsById(id)) {
			return new ErrorDataResult<Customer>(customerDao.findById(id).get(), Messages.customerNotFound); 
		}
		return new SuccessDataResult<Customer>();
				
	}
	
	
}
