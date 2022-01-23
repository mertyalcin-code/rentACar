package com.btkAkademi.rentACar.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import com.btkAkademi.rentACar.entities.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{

		User findByEmail(String email);
}
