package com.bank.service;

import java.util.List;

import com.bank.bean.Account;

public interface AccountServiceImp {
	public Account saveAccount(Account ac);

	public List<Account> getAllAccounts();

	public Account getAccountById(Long id);

	public void deleteAllAccounts();

	public void deleteAccount(Account ac);

	public List<Account> getByAccountType(String accountType);

	public Account getAccountByAccountNumber(Long accountNumber);

	public Boolean isAccountExists(Long id);

	public Boolean isAccountNumberExists(Long id);

}
