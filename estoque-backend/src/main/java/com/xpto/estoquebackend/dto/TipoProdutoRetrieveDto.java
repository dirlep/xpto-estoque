package com.xpto.estoquebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TipoProdutoRetrieveDto {

	private Long codigo;

	private String descricao;

	private String tipoProduto;

	private Integer quantidadeEstoque;

	private Integer quantidadeSaida;

}
