package com.bank.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler{
	@ExceptionHandler(value = AccountNotFoundException.class)
	public ResponseEntity<ErrorDetails> AccountEx(AccountNotFoundException ex)
	{
		ErrorDetails emodel = new ErrorDetails(404,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodel,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = AlreadyAccountExitException.class)
	public ResponseEntity<ErrorDetails> AccountEx(AlreadyAccountExitException ex)
	{
		ErrorDetails emodel = new ErrorDetails(400,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodel,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> ResourceNotFoundEx(ResourceNotFoundException ex)
	{
		ErrorDetails emodel = new ErrorDetails(400,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodel,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<ErrorDetails> BadCredentialEx(BadRequestException ex)
	{
		ErrorDetails emodel = new ErrorDetails(400,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodel,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = TransactionException.class)
	public ResponseEntity<ErrorDetails> transferEx(TransactionException ex)
	{
		ErrorDetails emodel = new ErrorDetails(400,ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodel,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
		
//		ex.getBindingResult().getAllErrors().forEach((error)->{
//			String fieldName=((FieldError)error).getField();
//			String message=error.getDefaultMessage();
//		});
		ObjectError error = ex.getBindingResult().getAllErrors().get(0);
		String message=error.getDefaultMessage();
		ErrorDetails emodle = new ErrorDetails(400, message);
		return new ResponseEntity<ErrorDetails>(emodle,HttpStatus.BAD_REQUEST);
	}
    
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalHandlerEx(Exception ex) {
		ErrorDetails emodle = new ErrorDetails(500, ex.getMessage());
		return new ResponseEntity<ErrorDetails>(emodle,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
