package com.xpto.estoquebackend.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentoEstoqueCreateDto {
	
	@NotNull(message = "Campo \"Tipo de Movimentação\" não pode ser nulo.")
	private String tipoMovimentacao;

	@NotNull(message = "Campo \"Produto\" não pode ser nulo.")
	private Long codigoProduto;

	private Double valorVenda;

	@NotNull(message = "Campo \"Data da Movimentação\" não pode ser nulo.")
	private String dataMovimentacao;

	@NotNull(message = "Campo \"Quantidade Movimentada\" não pode ser nulo.")
	@Min(value = 0L)
	private Integer quantidadeMovimentada;
}
