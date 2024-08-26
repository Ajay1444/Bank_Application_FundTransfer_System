package com.bank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.dao.UserRepository;
import com.bank.exception.ResourceNotFoundException;
@Service
public class CustomUserDetails implements UserDetailsService {
	@Autowired
	private UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.ur.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("Username not found "));
		
	}
	

}
