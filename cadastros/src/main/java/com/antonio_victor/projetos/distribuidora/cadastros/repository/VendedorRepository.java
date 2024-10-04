package com.antonio_victor.projetos.distribuidora.cadastros.repository;

import com.antonio_victor.projetos.distribuidora.cadastros.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findAllByNomeContainingIgnoreCase(String nome);
}