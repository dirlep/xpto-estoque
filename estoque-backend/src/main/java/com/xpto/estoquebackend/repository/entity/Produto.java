package com.xpto.estoquebackend.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	
	private static final long serialVersionUID = -5758906377980934308L;

	@Id
	@Column(nullable = false)
	private Long codigo;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false)
	private String tipoProduto;

	@Column(nullable = false)
	private Double valorFornecedor;

	@Column(nullable = false)
	private Integer quantidadeEstoque;

	private Integer quantidadeSaida;
	
	public Produto() {}

	public Produto(Long codigo, String descricao, String tipoProduto, Double valorFornecedor, Integer quantidadeEstoque,
			Integer quantidadeSaida) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipoProduto = tipoProduto;
		this.valorFornecedor = valorFornecedor;
		this.quantidadeEstoque = quantidadeEstoque;
		this.quantidadeSaida = quantidadeSaida;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Double getValorFornecedor() {
		return valorFornecedor;
	}

	public void setValorFornecedor(Double valorFornecedor) {
		this.valorFornecedor = valorFornecedor;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getQuantidadeSaida() {
		return quantidadeSaida;
	}

	public void setQuantidadeSaida(Integer quantidadeSaida) {
		this.quantidadeSaida = quantidadeSaida;
	}
}
