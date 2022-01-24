package com.btkAkademi.rentACar.business.abstracts;

import java.util.List;

import com.btkAkademi.rentACar.business.dtos.UserListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.entities.concretes.User;

public interface UserService {
	DataResult<User> findByEmail(String Email);

	DataResult<List<UserListDto>> findAll();

	Result delete(int id);
}
