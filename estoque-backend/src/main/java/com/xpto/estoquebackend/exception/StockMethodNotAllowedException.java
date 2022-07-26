package com.xpto.estoquebackend.exception;

public class StockMethodNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = -8802986809346343459L;

	public StockMethodNotAllowedException(String message) {
		super(message);
	}

	public StockMethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
