package com.xpto.estoquebackend.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6580673658607296534L; 
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
