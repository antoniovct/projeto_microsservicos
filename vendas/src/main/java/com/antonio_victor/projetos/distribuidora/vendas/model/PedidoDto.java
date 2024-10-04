package com.antonio_victor.projetos.distribuidora.vendas.model;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * DTO for {@link Pedido}
 */
public record PedidoDto(@NotBlank String vendedor, @NotBlank String cliente,
                         List<String> produtos)  {
}