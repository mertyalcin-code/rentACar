package com.btkAkademi.rentACar.core.utilities.loaders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.dataAccess.abstracts.UserDao;
import com.btkAkademi.rentACar.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminDataLoader implements CommandLineRunner{
	private UserDao userDao;

	@Override
	public void run(String... args) throws Exception {
		this.loadEmployee();
		
	}
	private void loadEmployee(){
		String role="EMPLOYEE";
		if(userDao.findByRole(role) == null) {
			userDao.save(new User(0,"admin","admin","EMPLOYEE"));
		}
	}

}
