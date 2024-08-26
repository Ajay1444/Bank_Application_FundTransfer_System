package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bean.Account;
import com.bank.dao.AccountRepository;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.ResourceNotFoundException;

@Service
public class AccountService implements AccountServiceImp {

	@Autowired
	private AccountRepository ar;

	@Override
	public Account saveAccount(Account ac) {
		return ar.save(ac);
	}

	@Override
	public List<Account> getAllAccounts() {

		return ar.findAll();
	}

	@Override
	public Account getAccountById(Long id) {

		return ar.findById(id).orElseThrow(() -> new ResourceNotFoundException(+id + " does not found "));

	}

	@Override
	public void deleteAccount(Account ac) {

		ar.delete(ac);
	}

	@Override
	public void deleteAllAccounts() {

		ar.deleteAll();
	}

	@Override
	public Boolean isAccountExists(Long id) {

		return ar.existsById(id);
	}

	@Override
	public Boolean isAccountNumberExists(Long accountNumber) {

		return ar.existsByAccountNumber(accountNumber);
	}

	@Override
	public Account getAccountByAccountNumber(Long accountNumber) {

		return this.ar.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException(+accountNumber + " Account Number does not found "));
	}

	@Override
	public List<Account> getByAccountType(String accountType) {

		List<Account> account = ar.findByAccountType(accountType);
		if (!account.isEmpty())
			return account;
		else
			throw new AccountNotFoundException("No account found of type: " + accountType);
	}

}
