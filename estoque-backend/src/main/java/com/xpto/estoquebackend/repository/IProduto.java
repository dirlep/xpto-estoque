package com.xpto.estoquebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpto.estoquebackend.repository.entity.Produto;

public interface IProduto extends JpaRepository<Produto, Long> {

	Optional<Produto> findByDescricao(String descricao);

	List<Produto> findByTipoProdutoIgnoreCase(String tipo);

	void deleteByCodigo(Long codigo);
	
}
