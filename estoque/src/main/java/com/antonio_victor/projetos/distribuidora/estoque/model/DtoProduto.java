package com.antonio_victor.projetos.distribuidora.estoque.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DtoProduto(
        @NotBlank
        String descricao,
        @NotNull
        @Positive
        Double preco,
        @NotNull
        @Positive
        Integer quantidade,
        @NotNull
        @Positive
        Long idMarca
) {
}
