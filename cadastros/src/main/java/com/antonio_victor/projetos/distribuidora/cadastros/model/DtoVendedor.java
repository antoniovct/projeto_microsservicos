package com.antonio_victor.projetos.distribuidora.cadastros.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


/**
 * DTO for {@link Vendedor}
 */
public record DtoVendedor(@NotBlank String nome, @NotBlank String telefone, @Email @NotBlank String email,
                          @NotBlank String cpf, @NotBlank String endereco, @NotBlank String cidade,
                          @NotBlank String cep) {
}