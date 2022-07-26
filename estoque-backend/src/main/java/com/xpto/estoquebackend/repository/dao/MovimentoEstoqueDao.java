package com.xpto.estoquebackend.repository.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xpto.estoquebackend.repository.IMovimentoEstoque;
import com.xpto.estoquebackend.repository.entity.MovimentoEstoque;

@Component
public class MovimentoEstoqueDao {

	@Autowired
	private IMovimentoEstoque repository;
	
	public MovimentoEstoque stockEntry(MovimentoEstoque entity) {
		return repository.save(entity);
	}

	public MovimentoEstoque stockExit(MovimentoEstoque entity) {
		return repository.save(entity);
	}
	
	public List<MovimentoEstoque> retrieveAll() {
		return repository.findAll();
	}
	
	public Integer delete(List<MovimentoEstoque> lista) {
		repository.deleteAll(lista);
		return 1;
	}
	
}
