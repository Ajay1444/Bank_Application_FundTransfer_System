package com.bank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bean.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
	List<Account> findByAccountType(String accountType);
	Optional<Account> findByAccountNumber(Long accountNumber);
	boolean existsByAccountNumber(Long accountNumber);
}
