package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.UserService;
import com.btkAkademi.rentACar.business.dtos.UserListDto;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	// Dependencies
	private UserService userService;

	// Dependency Injection
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// lists all users
	@GetMapping("find-all")
	public DataResult<List<UserListDto>> findAll() {
		return this.userService.findAll();
	}

	// deletes a user
	@DeleteMapping("delete/{id}")
	public Result delete(@Valid @PathVariable int id) {

		return this.userService.delete(id);
	}

}
