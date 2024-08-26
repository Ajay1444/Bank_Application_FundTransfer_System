package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.security.JWTHelper;
import com.bank.security.JWTRequest;
import com.bank.security.JWTResponse;

@RestController
public class AuthController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTHelper jwtHelper;
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<JWTResponse> generateToken(@RequestBody JWTRequest request) {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails user = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtHelper.generateToken(user);
		JWTResponse jwtResponse = new JWTResponse();
		jwtResponse.setToken(token);
		return new ResponseEntity<JWTResponse>(jwtResponse, HttpStatus.OK);

	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authenticate);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Details ");
		}

	}
}
