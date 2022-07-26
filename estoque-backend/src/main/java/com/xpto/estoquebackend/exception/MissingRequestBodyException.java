package com.xpto.estoquebackend.exception;

public class MissingRequestBodyException extends RuntimeException {
	
	private static final long serialVersionUID = 1478564977986838592L;

	public MissingRequestBodyException(String message) {
		super(message);
	}

	public MissingRequestBodyException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
