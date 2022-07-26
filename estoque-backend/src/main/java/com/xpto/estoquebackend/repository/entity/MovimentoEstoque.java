package com.xpto.estoquebackend.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estoque")
public class MovimentoEstoque implements Serializable {

	private static final long serialVersionUID = 1061025859415658239L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "produto_codigo")
	private Produto produto;

	@Column(nullable = false)
	private String tipoMovimentacao;

	@Column(nullable = true)
	private Double valorVenda;

	@Column(nullable = false)
	private String dataMovimentacao;

	@Column(nullable = false)
	private Integer quantidadeMovimentada;

	public MovimentoEstoque() {
	}

	public MovimentoEstoque(Long id, Produto produto, String tipoMovimentacao, Double valorVenda,
			String dataMovimentacao, Integer quantidadeMovimentada) {
		this.id = id;
		this.produto = produto;
		this.tipoMovimentacao = tipoMovimentacao;
		this.valorVenda = valorVenda;
		this.dataMovimentacao = dataMovimentacao;
		this.quantidadeMovimentada = quantidadeMovimentada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Integer getQuantidadeMovimentada() {
		return quantidadeMovimentada;
	}

	public void setQuantidadeMovimentada(Integer quantidadeMovimentada) {
		this.quantidadeMovimentada = quantidadeMovimentada;
	}

}
