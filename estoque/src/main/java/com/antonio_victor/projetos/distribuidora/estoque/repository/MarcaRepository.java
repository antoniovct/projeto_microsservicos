package com.antonio_victor.projetos.distribuidora.estoque.repository;

import com.antonio_victor.projetos.distribuidora.estoque.model.Marca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findAllByNomeContainingIgnoreCase(String nome);

}
