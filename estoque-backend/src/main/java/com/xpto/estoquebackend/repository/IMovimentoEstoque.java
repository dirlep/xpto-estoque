package com.xpto.estoquebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpto.estoquebackend.repository.entity.MovimentoEstoque;

public interface IMovimentoEstoque extends JpaRepository<MovimentoEstoque, Long> {

}
