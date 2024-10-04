package com.antonio_victor.projetos.distribuidora.cadastros.repository;

import com.antonio_victor.projetos.distribuidora.cadastros.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findAllByNomeFantasiaContainingIgnoreCase(String nome);
    List<Cliente> findAllByRazaoSocialContainingIgnoreCase(String nome);
}