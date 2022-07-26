package com.xpto.estoquebackend.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xpto.estoquebackend.dto.LucroProdutoRetrieveDto;
import com.xpto.estoquebackend.dto.ProdutoCreateDto;
import com.xpto.estoquebackend.dto.ProdutoRetrieveDto;
import com.xpto.estoquebackend.dto.ProdutoUpdateDto;
import com.xpto.estoquebackend.dto.TipoProdutoRetrieveDto;
import com.xpto.estoquebackend.exception.DataNotFoundException;
import com.xpto.estoquebackend.service.ProdutoService;

@Validated
@RestController
@RequestMapping(path = "/api/v1/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	private static final String MSG_NOT_FOUND = "Não foram encontrados registros na base de dados.";

	@PostMapping
	public ResponseEntity<ProdutoRetrieveDto> create(@Valid @RequestBody ProdutoCreateDto request) {
		Long id = service.create(request);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{codigo}")
	public ResponseEntity<ProdutoRetrieveDto> retrieve(@PathVariable Long codigo) {
		return ResponseEntity.ok(service.retrieve(codigo));
	}

	@GetMapping
	public ResponseEntity<List<ProdutoRetrieveDto>> retrieveAll() {
		List<ProdutoRetrieveDto> retrieved = service.retrieveAll();
		if (!retrieved.isEmpty()) {
			return ResponseEntity.ok(retrieved);
		}
		throw new DataNotFoundException(MSG_NOT_FOUND);
	}

	@GetMapping(path = "/tipo/{tipo}")
	public ResponseEntity<List<TipoProdutoRetrieveDto>> retrieveAllByType(@PathVariable String tipo) {
		List<TipoProdutoRetrieveDto> retrieved = service.retrieveAllByType(tipo);
		if (!retrieved.isEmpty()) {
			return ResponseEntity.ok(retrieved);
		}
		throw new DataNotFoundException(MSG_NOT_FOUND);
	}

	@GetMapping(path = "/lucro")
	public ResponseEntity<List<LucroProdutoRetrieveDto>> retrieveProfit() {
		List<LucroProdutoRetrieveDto> retrieved = service.retrieveProfit();
		if (!retrieved.isEmpty()) {
			return ResponseEntity.ok(retrieved);
		}
		throw new DataNotFoundException(MSG_NOT_FOUND);
	}

	@PutMapping
	public ResponseEntity<Integer> update(@Valid @RequestBody(required = true) ProdutoUpdateDto request) {
		Integer rowsUpdated = service.update(request);
		return ResponseEntity.ok(rowsUpdated);
	}

	@DeleteMapping(path = "/{codigo}")
	public ResponseEntity<Integer> delete(@PathVariable Long codigo) {
		Integer rowsDeleted = service.delete(codigo);
		return ResponseEntity.ok(rowsDeleted);
	}
}
