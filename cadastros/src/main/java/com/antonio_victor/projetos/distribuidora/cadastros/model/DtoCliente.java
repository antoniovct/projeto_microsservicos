package com.antonio_victor.projetos.distribuidora.cadastros.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
public record DtoCliente(@NotBlank String razaoSocial, @NotBlank String nomeFantasia, @NotBlank String telefone,
                         @Email String email, @NotBlank String cnpj, @NotBlank String endereco, @NotBlank String cidade,
                         @NotBlank String cep) {
}