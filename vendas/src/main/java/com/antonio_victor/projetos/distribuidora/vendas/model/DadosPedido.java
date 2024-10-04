package com.antonio_victor.projetos.distribuidora.vendas.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Pedido}
 */
public record DadosPedido(Long id, String data, String vendedor, Cliente cliente,
                          List<ItemPedido> produtos) {
  public DadosPedido(Pedido pedido) {
    this(pedido.getId(), pedido.getData(), pedido.getVendedor(), pedido.getCliente(), pedido.getProduto());
  }
}