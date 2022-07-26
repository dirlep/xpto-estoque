package com.xpto.estoquebackend.handler;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xpto.estoquebackend.exception.DataNotFoundException;
import com.xpto.estoquebackend.exception.DataViolationException;
import com.xpto.estoquebackend.exception.InconsistentSaleValueException;
import com.xpto.estoquebackend.exception.InsuficientStockException;
import com.xpto.estoquebackend.exception.MissingRequestBodyException;
import com.xpto.estoquebackend.exception.StockMethodNotAllowedException;

@ControllerAdvice
public class ResourceExceptionHandler {

	public static final String MSG_ERROR = "Erro ao realizar a requisição.";

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<EstoqueHttpError> dataNotFoundException(DataNotFoundException e,
			HttpServletRequest request) {
		return ((BodyBuilder) ResponseEntity.notFound()).body(EstoqueHttpError.builder()
				.message("Não foram encontrados registros na base de dados.").detail(e.toString())
				.status(HttpStatus.NOT_FOUND.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EstoqueHttpError> argumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest()
				.body(EstoqueHttpError.builder()
						.message(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()).detail(e.toString())
						.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<EstoqueHttpError> dataIntegrityException(DataIntegrityViolationException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<EstoqueHttpError> transactionSystemException(ConstraintViolationException e) {
		return ResponseEntity.badRequest()
				.body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.getConstraintViolations().toString())
						.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<EstoqueHttpError> messageNotReadable(HttpMessageNotReadableException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<EstoqueHttpError> requestParameterException(MissingServletRequestParameterException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(MissingRequestBodyException.class)
	public ResponseEntity<EstoqueHttpError> missingRequestBody(MissingRequestBodyException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EstoqueHttpError> methodNotSupported(HttpRequestMethodNotSupportedException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(InsuficientStockException.class)
	public ResponseEntity<EstoqueHttpError> insuficientStock(InsuficientStockException e) {
		return ResponseEntity.badRequest()
				.body(EstoqueHttpError.builder().message("Quantidade insuficiente em estoque.").detail(e.toString())
						.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(InconsistentSaleValueException.class)
	public ResponseEntity<EstoqueHttpError> inconsistentSaleValue(InconsistentSaleValueException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder()
				.message("Valor de venda não pode ser nulo e precisa ser maior ou igual à 0.").detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(DataViolationException.class)
	public ResponseEntity<EstoqueHttpError> dataViolation(DataViolationException e) {
		return ResponseEntity.badRequest()
				.body(EstoqueHttpError.builder().message("Já existe um registro com esses dados.")
						.detail(e.toString()).status(HttpStatus.BAD_REQUEST.toString())
						.timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<EstoqueHttpError> nullPointer(NullPointerException e) {
		return ResponseEntity.badRequest().body(EstoqueHttpError.builder().message(MSG_ERROR).detail(e.toString())
				.status(HttpStatus.BAD_REQUEST.toString()).timestamp(LocalDateTime.now().toString()).build());
	}

	@ExceptionHandler(StockMethodNotAllowedException.class)
	public ResponseEntity<EstoqueHttpError> stockMethodNotAllowed(StockMethodNotAllowedException e) {
		return ResponseEntity.badRequest()
				.body(EstoqueHttpError.builder().message("Insira um Tipo de Movimentação válido.")
						.detail(e.toString()).status(HttpStatus.BAD_REQUEST.toString())
						.timestamp(LocalDateTime.now().toString()).build());
	}

}
