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
public class ProdutoCreateDto {
	
	@NotNull(message = "Campo \"Código\" não pode ser nulo.")
	private Long codigo;
	
	@NotNull(message = "Campo \"Descrição\" não pode ser nulo.")
	private String descricao;

	@NotNull(message = "Campo \"Tipo de Produto\" não pode ser nulo.")
	private String tipoProduto;

	@NotNull(message = "Campo \"Valor no Fornecedor\" não pode ser nulo.")
	@Min(value = 0L, message = "Campo \"Valor no Fornecedor\" precisa ser maior ou igual à 0.")
	private Double valorFornecedor;

	@Min(value = 0L, message = "Campo \"Quantidade em Estoque\" precisa ser maior ou igual à 0.")
	private Integer quantidadeEstoque;
	
}
