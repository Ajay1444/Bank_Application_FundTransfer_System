package com.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bank.bean.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	

}
