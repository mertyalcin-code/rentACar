package com.btkAkademi.rentACar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.entities.concretes.User;

@Service
public class PrimitiveAuthManager implements AuthService {
	private UserService userService;

	@Autowired
	public PrimitiveAuthManager(UserService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public DataResult<LoginResponse> login(LoginRequest loginRequest) {
		Result result = BusinessRules.run(
				checkIfUserExistsByEmail(loginRequest.getEmail()),
				checkIfUsersPasswordIsCorrect(loginRequest.getEmail(), loginRequest.getPassword()));
		
		if (result != null) {
			return new ErrorDataResult<>(result.getMessage());
		}
		
		User user = userService.findByEmail(loginRequest.getEmail()).getData();
		LoginResponse response = new LoginResponse();
		response.setId(user.getId());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		return new SuccessDataResult<LoginResponse>(response, Messages.LOGINSUCCESS);

	}

	// controls there is a user with that email
	private Result checkIfUserExistsByEmail(String email) {
		if (userService.findByEmail(email).getData()==null) {
			return new ErrorResult(Messages.USERNOTFOUND);
		} else
			return new SuccessResult();

	}

	// checks if password correct //fluid pattern ile mi 
	private Result checkIfUsersPasswordIsCorrect(String email, String password) {		
		User user = userService.findByEmail(email).getData();		
		if (user!=null && !password.equals(user.getPassword())) {
			return new ErrorResult(Messages.LOGINPASSWORDERROR);
		} else
			return new SuccessResult();

	}

}
