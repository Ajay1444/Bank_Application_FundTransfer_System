package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyAccountExitException extends RuntimeException {

	public AlreadyAccountExitException(String msg)
	{
		super(msg);
	}
}
