package com.btkAkademi.rentACar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.core.utilities.results.DataResult;
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;
	@Autowired
	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	@PostMapping("login")
	public DataResult<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		return this.authService.login(loginRequest);
	}
	}
