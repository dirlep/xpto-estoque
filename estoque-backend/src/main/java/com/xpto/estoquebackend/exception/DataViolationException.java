package com.xpto.estoquebackend.exception;

public class DataViolationException extends RuntimeException {

	private static final long serialVersionUID = 8061285092180869486L;
	
	public DataViolationException(String message) {
		super(message);
	}
	
	public DataViolationException(String message, Throwable cause) {
		super(message, cause);
	}

}
