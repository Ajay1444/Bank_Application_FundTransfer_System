package com.bank.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TransactionException extends RuntimeException {
	public TransactionException(String msg)
	{
		super(msg);
	}

}
