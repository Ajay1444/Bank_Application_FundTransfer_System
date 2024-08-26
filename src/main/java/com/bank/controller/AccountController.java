package com.bank.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bean.Account;
import com.bank.exception.AccountNotFoundException;
import com.bank.exception.AlreadyAccountExitException;
import com.bank.exception.TransactionException;
import com.bank.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService as;

	@GetMapping("/accounts")
	public ResponseEntity<?> getAllAcc() {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();
		List<Account> lst = as.getAllAccounts();
		if (!lst.isEmpty()) {
			jsonOutput.put("status", 200);
			jsonOutput.put("data", lst);
			return new ResponseEntity<>(jsonOutput, HttpStatus.OK);
		} else
			throw new AccountNotFoundException("No Account found");
	}

	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<?> getAccBtId(@PathVariable Long accountNumber) {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();

		Account ac = as.getAccountByAccountNumber(accountNumber);

		jsonOutput.put("status", 200);
		jsonOutput.put("data", ac);
		return new ResponseEntity<>(jsonOutput, HttpStatus.OK);
	}

	@PostMapping("/account")
	public ResponseEntity<?> createAccount(@Valid @RequestBody Account ac) {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();
		if (as.isAccountNumberExists(ac.getAccountNumber()))
			throw new AlreadyAccountExitException(ac.getAccountNumber() + " This Account Number already Exist");

		Account account = as.saveAccount(ac);
		jsonOutput.put("status", 201);
		jsonOutput.put("message", "Account Created");
		jsonOutput.put("data", account);
		return new ResponseEntity<>(jsonOutput, HttpStatus.CREATED);
	}

	@DeleteMapping("/account/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable Long accountNumber) {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();
		if (!as.isAccountNumberExists(accountNumber))
			throw new AccountNotFoundException(accountNumber + " This account number does not exist");

		Account account = as.getAccountByAccountNumber(accountNumber);
		as.deleteAccount(account);
		jsonOutput.put("status", 200);
		jsonOutput.put("message", "Account Deleted");
		return new ResponseEntity<>(jsonOutput, HttpStatus.OK);
	}

	@DeleteMapping("/accounts")
	public ResponseEntity<?> deleteAllAccount() {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();
		as.deleteAllAccounts();
		jsonOutput.put("status", 200);
		jsonOutput.put("message", "All Accounts Deleted");
		return new ResponseEntity<>(jsonOutput, HttpStatus.OK);
	}

	@PutMapping("/account/{accountNumber}")
	public ResponseEntity<?> updateAccount(@PathVariable Long accountNumber, @RequestBody Account ac) {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();
		Account account = as.getAccountByAccountNumber(accountNumber);
		account.setAccountType(ac.getAccountType());
		account.setBalance(ac.getBalance());
		account.setCustomer(ac.getCustomer());
		as.saveAccount(account);
		// customer.setAddress(ac.getCustomer().getAddress());
		jsonOutput.put("status", 200);
		jsonOutput.put("messsage", "Account updated");
		jsonOutput.put("data", as.getAccountByAccountNumber(accountNumber));

		return new ResponseEntity<>(jsonOutput, HttpStatus.OK);
	}

	@PutMapping("/fundTransfer/{fromAccount}/{toAccount}/{amount}")
	public ResponseEntity<?> fundTransfer(@PathVariable Long fromAccount, @PathVariable Long toAccount,
			@PathVariable Double amount) {
		Map<String, Object> jsonOutput = new LinkedHashMap<String, Object>();

		if (!as.isAccountNumberExists(toAccount))
			throw new AccountNotFoundException("Invalid Destination account");

		if (!as.isAccountNumberExists(fromAccount))
			throw new AccountNotFoundException("Invalid Source account");

		if (fromAccount == toAccount)
			throw new TransactionException("Source and Destination account cannot be same");

		if (amount < 1)
			throw new TransactionException("Minimum Amount allowed is 1");

		Account FromAcc = as.getAccountByAccountNumber(fromAccount);
		Account ToAcc = as.getAccountByAccountNumber(toAccount);

		if (FromAcc.getBalance() < amount)
			throw new TransactionException("Insufficient Balance");

		Double fromAccountUpdatedBalance = FromAcc.getBalance() - amount;
		FromAcc.setBalance(fromAccountUpdatedBalance);
		as.saveAccount(FromAcc);

		Double toAccountUpdatedBalance = ToAcc.getBalance() + amount;
		ToAcc.setBalance(toAccountUpdatedBalance);
		as.saveAccount(ToAcc);

		jsonOutput.put("status", 200);
		jsonOutput.put("messsage", "Transaction Successful");
		return new ResponseEntity<>(jsonOutput, HttpStatus.OK);

	}

}
