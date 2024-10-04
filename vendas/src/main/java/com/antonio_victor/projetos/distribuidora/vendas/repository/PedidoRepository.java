package com.antonio_victor.projetos.distribuidora.vendas.repository;

import com.antonio_victor.projetos.distribuidora.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByVendedor(String vendedor);
}