package com.xpto.estoquebackend.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xpto.estoquebackend.exception.DataNotFoundException;
import com.xpto.estoquebackend.repository.IProduto;
import com.xpto.estoquebackend.repository.entity.Produto;

@Component
public class ProdutoDao {

	@Autowired
	private IProduto repository;

	public Produto create(Produto entity) {
		return repository.save(entity);
	}

	public Produto retrieveEntity(Long codigo) {
		Optional<Produto> entity = repository.findById(codigo);
		return entity.orElseThrow(() -> new DataNotFoundException(String.format("Produto não encontrado, código: %s", codigo.toString())));
	}
	
	public Optional<Produto> retrieve(Long codigo) {
		return repository.findById(codigo);
	}
	
	public Optional<Produto> retrieveDescription(String descricao) {
		return repository.findByDescricao(descricao);
	}

	public List<Produto> retrieveAll() {
		return repository.findAll();
	}

	public Integer update(Produto entity) {
		repository.save(entity);
		return 1;
	}
	
	public Integer delete(Long codigo) {
		repository.deleteByCodigo(codigo);
		return 1;
	}

	public List<Produto> retrieveAllByType(String tipo) {
		return repository.findByTipoProdutoIgnoreCase(tipo);
	}
	
}
