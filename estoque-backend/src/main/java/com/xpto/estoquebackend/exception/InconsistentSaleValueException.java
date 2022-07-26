package com.xpto.estoquebackend.exception;

public class InconsistentSaleValueException extends RuntimeException {

	private static final long serialVersionUID = -7279047974872755641L;

	public InconsistentSaleValueException(String message) {
		super(message);
	}
	
	public InconsistentSaleValueException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
