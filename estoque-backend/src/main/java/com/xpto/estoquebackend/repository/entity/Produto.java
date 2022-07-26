package com.xpto.estoquebackend.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	
}
