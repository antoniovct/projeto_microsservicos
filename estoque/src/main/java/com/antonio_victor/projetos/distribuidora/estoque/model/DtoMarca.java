package com.antonio_victor.projetos.distribuidora.estoque.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoMarca(
        @NotBlank
        String nome,
        @NotNull
        Double comissao
) {
}
