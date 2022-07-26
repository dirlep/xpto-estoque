package com.xpto.estoquebackend.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xpto.estoquebackend.dto.MovimentoEstoqueCreateDto;
import com.xpto.estoquebackend.exception.StockMethodNotAllowedException;
import com.xpto.estoquebackend.service.MovimentoEstoqueService;

@Validated
@RestController
@RequestMapping(path = "/api/v1/estoque")
public class MovimentoEstoqueController {

	@Autowired
	private MovimentoEstoqueService service;

	@PostMapping
	public ResponseEntity<MovimentoEstoqueCreateDto> stock(@Valid @RequestBody MovimentoEstoqueCreateDto request) {
		Long id;
		if (request.getTipoMovimentacao().equalsIgnoreCase("entrada")) {
			id = service.stockEntry(request);
		} else if (request.getTipoMovimentacao().equalsIgnoreCase("saida")) {
			id = service.stockExit(request);
		} else {
			throw new StockMethodNotAllowedException("Insira um Tipo de Movimentação válido.");
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
}
