package com.xpto.estoquebackend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpto.estoquebackend.dto.LucroProdutoRetrieveDto;
import com.xpto.estoquebackend.dto.ProdutoCreateDto;
import com.xpto.estoquebackend.dto.ProdutoRetrieveDto;
import com.xpto.estoquebackend.dto.ProdutoUpdateDto;
import com.xpto.estoquebackend.dto.TipoProdutoRetrieveDto;
import com.xpto.estoquebackend.exception.DataViolationException;
import com.xpto.estoquebackend.repository.dao.MovimentoEstoqueDao;
import com.xpto.estoquebackend.repository.dao.ProdutoDao;
import com.xpto.estoquebackend.repository.entity.MovimentoEstoque;
import com.xpto.estoquebackend.repository.entity.Produto;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoDao daoProduto;

	@Autowired
	private MovimentoEstoqueDao daoEstoque;

	@Transactional
	public Long create(ProdutoCreateDto request) {
		Optional<Produto> opEntity = daoProduto.retrieve(request.getCodigo());

		if (opEntity.isPresent()) {
			throw new DataViolationException("Já existe um registro com esses dados.");
		}

		Produto entity = Produto.builder().codigo(request.getCodigo()).descricao(request.getDescricao())
				.tipoProduto(request.getTipoProduto()).valorFornecedor(request.getValorFornecedor())
				.quantidadeEstoque(0).quantidadeSaida(0).build();

		return daoProduto.create(entity).getCodigo();
	}

	@Transactional(readOnly = true)
	public ProdutoRetrieveDto retrieve(Long codigo) {
		Produto entity = daoProduto.retrieveEntity(codigo);

		return ProdutoRetrieveDto.builder().codigo(entity.getCodigo()).descricao(entity.getDescricao())
				.tipoProduto(entity.getTipoProduto()).valorFornecedor(entity.getValorFornecedor())
				.quantidadeEstoque(entity.getQuantidadeEstoque()).build();
	}

	@Transactional(readOnly = true)
	public List<ProdutoRetrieveDto> retrieveAll() {
		List<Produto> entityList = daoProduto.retrieveAll();

		return entityList.stream()
				.map((Produto item) -> ProdutoRetrieveDto.builder().codigo(item.getCodigo())
						.descricao(item.getDescricao()).tipoProduto(item.getTipoProduto())
						.valorFornecedor(item.getValorFornecedor()).quantidadeEstoque(item.getQuantidadeEstoque())
						.build())
				.collect(Collectors.toList());
	}

	@Transactional
	public Integer update(@Valid ProdutoUpdateDto request) {
		Optional<Produto> opEntity = daoProduto.retrieve(request.getCodigo());
		if (!opEntity.isPresent()) {
			return 0;
		}
		Produto entity = Produto.builder().codigo(request.getCodigo()).descricao(request.getDescricao())
				.tipoProduto(request.getTipoProduto()).valorFornecedor(request.getValorFornecedor())
				.quantidadeEstoque(opEntity.get().getQuantidadeEstoque())
				.quantidadeSaida(opEntity.get().getQuantidadeSaida()).build();
		return daoProduto.update(entity);
	}

	@Transactional
	public Integer delete(Long codigo) {
		Optional<Produto> opEntity = daoProduto.retrieve(codigo);
		if (!opEntity.isPresent()) {
			return 0;
		}

		List<MovimentoEstoque> listaEstoque = daoEstoque.retrieveAll().stream()
				.filter(itemEstoque -> itemEstoque.getProduto().getCodigo().equals(codigo))
				.collect(Collectors.toList());
		daoEstoque.delete(listaEstoque);

		return daoProduto.delete(codigo);
	}

	@Transactional(readOnly = true)
	public List<TipoProdutoRetrieveDto> retrieveAllByType(String tipo) {
		List<Produto> entityList = daoProduto.retrieveAllByType(tipo);

		return entityList.stream()
				.map((Produto item) -> TipoProdutoRetrieveDto.builder().codigo(item.getCodigo())
						.descricao(item.getDescricao()).tipoProduto(item.getTipoProduto())
						.quantidadeEstoque(item.getQuantidadeEstoque()).quantidadeSaida(item.getQuantidadeSaida())
						.build())
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<LucroProdutoRetrieveDto> retrieveProfit() {
		List<Produto> produtoList = daoProduto.retrieveAll();
		List<MovimentoEstoque> estoqueList = daoEstoque.retrieveAll().stream()
				.filter(itemEstoque -> itemEstoque.getTipoMovimentacao().equalsIgnoreCase("saida"))
				.collect(Collectors.toList());

		return produtoList.stream().map((Produto item) -> {
			Double lucro = 0.0;

			Optional<MovimentoEstoque> estoque = estoqueList.stream()
					.filter(itemEstoque -> itemEstoque.getProduto().getCodigo().equals(item.getCodigo())).findFirst();

			if (estoque.isPresent()) {
				lucro = (estoque.get().getValorVenda() - item.getValorFornecedor()) * item.getQuantidadeSaida();
			}

			return LucroProdutoRetrieveDto.builder().codigo(item.getCodigo()).descricao(item.getDescricao())
					.tipoProduto(item.getTipoProduto()).quantidadeEstoque(item.getQuantidadeEstoque())
					.quantidadeSaida(item.getQuantidadeSaida()).totalLucro(lucro).build();
		}).collect(Collectors.toList());
	}
}
