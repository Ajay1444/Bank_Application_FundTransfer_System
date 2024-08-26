package com.bank.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bean.Account;
import com.bank.security.bean.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String  username);

}
