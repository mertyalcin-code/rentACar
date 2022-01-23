package com.btkAkademi.rentACar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.entities.concretes.User;

@Service
public class PrimitiveAuthManager implements AuthService{
	private UserService userService;
	
	@Autowired
	public PrimitiveAuthManager(UserService userService) {
		super();
		this.userService = userService;
	}


	@Override
	public DataResult<LoginResponse> login(LoginRequest loginRequest) {
	
		User user = userService.findByEmail(loginRequest.getEmail()).getData();
		if(userService.findByEmail(loginRequest.getEmail()).getData()!=null) {
			if(loginRequest.getPassword().equals(user.getPassword())) {
				LoginResponse response = new LoginResponse();
				response.setId(user.getId());
				response.setEmail(user.getEmail());
				response.setRole(user.getRole());
				return new SuccessDataResult<LoginResponse>(response,Messages.LIST); //giriş başarılı desin
			}else return new ErrorDataResult<LoginResponse>(Messages.NOTFOUND); //şifre yanlış desin
		}
		return new ErrorDataResult<LoginResponse>(Messages.NOTFOUND);
	}
}
