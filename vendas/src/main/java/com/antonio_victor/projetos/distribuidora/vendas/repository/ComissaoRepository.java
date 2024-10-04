package com.antonio_victor.projetos.distribuidora.vendas.repository;

import com.antonio_victor.projetos.distribuidora.vendas.model.Comissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComissaoRepository extends JpaRepository<Comissao, Long> {
}