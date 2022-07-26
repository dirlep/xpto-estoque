package com.xpto.estoquebackend.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpto.estoquebackend.dto.MovimentoEstoqueCreateDto;
import com.xpto.estoquebackend.exception.InconsistentSaleValueException;
import com.xpto.estoquebackend.exception.InsuficientStockException;
import com.xpto.estoquebackend.repository.dao.MovimentoEstoqueDao;
import com.xpto.estoquebackend.repository.dao.ProdutoDao;
import com.xpto.estoquebackend.repository.entity.MovimentoEstoque;
import com.xpto.estoquebackend.repository.entity.Produto;

@Service
public class MovimentoEstoqueService {

	@Autowired
	private MovimentoEstoqueDao dao;

	@Autowired
	private ProdutoDao produtoDao;
	
	public Long stockEntry(@Valid MovimentoEstoqueCreateDto request) {
		Produto produto = produtoDao.retrieveEntity(request.getCodigoProduto());

		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + request.getQuantidadeMovimentada());

		MovimentoEstoque entity = MovimentoEstoque.builder().produto(produto)
				.tipoMovimentacao(request.getTipoMovimentacao()).dataMovimentacao(request.getDataMovimentacao())
				.quantidadeMovimentada(request.getQuantidadeMovimentada()).valorVenda(request.getValorVenda()).build();

		return dao.stockEntry(entity).getId();
	}

	public Long stockExit(@Valid MovimentoEstoqueCreateDto request) {
		if (request.getValorVenda() == null || request.getValorVenda() < 0) {
			throw new InconsistentSaleValueException("Valor de venda não pode ser nulo e precisa ser maior ou igual à 0.");
		}

		Produto produto = produtoDao.retrieveEntity(request.getCodigoProduto());

		if (produto.getQuantidadeEstoque() <= 0
				|| produto.getQuantidadeEstoque() < request.getQuantidadeMovimentada()) {
			throw new InsuficientStockException("Quantidade insuficiente em estoque.");
		}

		Integer quantidadeSaida = produto.getQuantidadeSaida();
		Integer quantidadeMovimentada = request.getQuantidadeMovimentada();

		produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeMovimentada);
		produto.setQuantidadeSaida(quantidadeSaida += quantidadeMovimentada);

		MovimentoEstoque entity = MovimentoEstoque.builder().produto(produto)
				.tipoMovimentacao(request.getTipoMovimentacao()).valorVenda(request.getValorVenda())
				.dataMovimentacao(request.getDataMovimentacao()).quantidadeMovimentada(request.getQuantidadeMovimentada())
				.build();

		return dao.stockExit(entity).getId();
	}
}
