package com.antonio_victor.projetos.distribuidora.estoque.repository;

import com.antonio_victor.projetos.distribuidora.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByDescricaoContainingIgnoreCase(String nome);
    List<Produto> findAllByIdMarca(Long idMarca);
}
