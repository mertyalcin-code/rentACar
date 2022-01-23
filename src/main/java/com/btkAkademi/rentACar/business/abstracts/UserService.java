package com.btkAkademi.rentACar.business.abstracts;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.entities.concretes.User;

public interface UserService {
	DataResult<User> findByEmail(String Email);
}
