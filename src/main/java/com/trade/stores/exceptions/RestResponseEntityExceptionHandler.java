package com.trade.stores.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CustomDataException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public Message customDataException(CustomDataException ex, WebRequest request) {
		Message message = new Message(HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
		return message;
	}
	
	@ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	  public Message globalExceptionHandler(Exception ex, WebRequest request) {
	    Message message = new Message(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
}