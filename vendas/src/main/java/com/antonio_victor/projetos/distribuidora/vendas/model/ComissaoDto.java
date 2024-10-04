package com.antonio_victor.projetos.distribuidora.vendas.model;

import java.io.Serializable;

/**
 * DTO for {@link Comissao}
 */
public record ComissaoDto(
        String dataInicial,
        String dataFinal) {
}