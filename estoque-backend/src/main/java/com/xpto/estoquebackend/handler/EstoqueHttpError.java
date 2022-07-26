package com.xpto.estoquebackend.handler;

import org.springframework.http.HttpStatus;

public class EstoqueHttpError {

	private String message;

	private String detail;

	private String status;

	private String timestamp;
	
	public EstoqueHttpError() {}

	public EstoqueHttpError(String message, String detail, String status, String timestamp) {
		this.message = message;
		this.detail = detail;
		this.status = status;
		this.timestamp = timestamp;
	}

	public EstoqueHttpError(String message, String detail, HttpStatus httpStatus, String timestamp) {
		this.message = message;
		this.detail = detail;
		this.status = httpStatus.toString();
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
