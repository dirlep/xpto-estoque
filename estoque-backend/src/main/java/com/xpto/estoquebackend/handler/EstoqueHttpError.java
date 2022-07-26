package com.xpto.estoquebackend.handler;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EstoqueHttpError {

	private String message;

	private String detail;

	private String status;

	private String timestamp;

	public EstoqueHttpError(String message, String detail, HttpStatus httpStatus, String timestamp) {
		this.message = message;
		this.detail = detail;
		this.status = httpStatus.toString();
		this.timestamp = timestamp;
	}

}
