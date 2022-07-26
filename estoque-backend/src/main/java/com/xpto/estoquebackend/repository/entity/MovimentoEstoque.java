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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_estoque")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
