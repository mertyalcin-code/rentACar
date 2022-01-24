package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.UserListDto;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.UserDao;
import com.btkAkademi.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService {
	// Dependencies
	private UserDao userDao;
	private ModelMapperService modelMapperService;

	// Dependency Injection
	@Autowired
	public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
		super();
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
	}

	// Finds User By Email
	@Override
	public DataResult<User> findByEmail(String Email) {

		return new SuccessDataResult<User>(userDao.findByEmail(Email), Messages.USERFOUND);
	}

	// Finds all users in the system
	@Override
	public DataResult<List<UserListDto>> findAll() {
		List<User> userList = this.userDao.findAll();
		List<UserListDto> response = userList.stream()
				.map(user -> modelMapperService.forDto().map(user, UserListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<UserListDto>>(response, Messages.USERLIST);

	}

	// Deletes a user if there is no relation
	@Override
	public Result delete(int id) {
		if (userDao.existsById(id)) {
			userDao.deleteById(id);
			return new SuccessResult(Messages.CUSTOMERDELETE);
		} else
			return new ErrorResult(Messages.NOTFOUND);
	}

}
