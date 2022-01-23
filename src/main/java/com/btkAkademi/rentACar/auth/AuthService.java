package com.btkAkademi.rentACar.auth;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;

public interface AuthService {
	
	DataResult<LoginResponse> login(LoginRequest loginRequest);

}
