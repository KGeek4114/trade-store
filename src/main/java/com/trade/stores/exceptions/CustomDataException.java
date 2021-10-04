package com.trade.stores.exceptions;

public class CustomDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CustomDataException(String errorMessage) {
		super(errorMessage);  
	}
}