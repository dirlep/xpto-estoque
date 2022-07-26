package com.xpto.estoquebackend.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpto.estoquebackend.dto.ProdutoCreateDto;
import com.xpto.estoquebackend.service.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;
	
	@Test
	void createProduto_passingValidRequest_createSuccess() throws Exception {
		// Instance new Objects
		ProdutoCreateDto request = ProdutoCreateDto.builder().codigo(1l).descricao("Produto Teste 01")
				.tipoProduto("Eletrônico").valorFornecedor(235.00)
				.quantidadeEstoque(0).build();

		// Mock calls
		when(service.create(request)).thenReturn(-1L);
		when(service.create(any(ProdutoCreateDto.class))).thenReturn(-1L);

		// Execution
		//@formatter:off
		MvcResult result = 
			mockMvc
				.perform(post("/api/v1/produto")
				.content(new ObjectMapper()
				.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(header().exists("Location"))
				.andReturn();
		//@formatter:on

		// Asserts
		String location = result.getResponse().getHeader("Location");
		assertThat(location, Matchers.endsWith("/api/v1/produto/-1"));

		// Verifications
		verify(service).create(request);
		verifyNoMoreInteractions(service);
	}
	
	@Test
	void createProduto_passingBlankRequest_badRequest() throws Exception {
		// Instance new Objects
		ProdutoCreateDto request = ProdutoCreateDto.builder().codigo(null).descricao(null)
				.tipoProduto(null).valorFornecedor(null)
				.quantidadeEstoque(null).build();
		
		// Execution
		//@formatter:off
		mockMvc
			.perform(post("/api/v1/produto")
			.content(new ObjectMapper()
			.writeValueAsString(request))
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isBadRequest());
		//@formatter:on

		// Verifications
		verifyNoMoreInteractions(service);
	}
	
}
