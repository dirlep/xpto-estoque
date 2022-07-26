package com.xpto.estoquebackend.exception;

public class InsuficientStockException extends RuntimeException {
	
	private static final long serialVersionUID = -3437038978627354429L;

	public InsuficientStockException(String message) {
		super(message);
	}
	
	public InsuficientStockException(String message, Throwable cause) {
		super(message, cause);
	}
}
