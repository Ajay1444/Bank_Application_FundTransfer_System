package com.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
public class Bank2036311AApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder pe;
	public static void main(String[] args) {
		SpringApplication.run(Bank2036311AApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.pe.encode("ajay1444"));
		
	}

}
