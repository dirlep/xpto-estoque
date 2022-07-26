package com.xpto.estoquebackend.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.xpto.estoquebackend.repository.dao.ProdutoDao;
import com.xpto.estoquebackend.service.ProdutoService;

@TestConfiguration
public class EstoqueTestConfig {

	@Bean
	@Primary
	public ProdutoDao getProdutoDao() {
		return Mockito.mock(ProdutoDao.class);
	}
	
	@Bean
	@Primary
	public ProdutoService getProdutoService() {
		return Mockito.mock(ProdutoService.class);
	}
}
