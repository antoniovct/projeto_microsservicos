package com.antonio_victor.projetos.distribuidora.vendas.model;

public record ClienteDto(
        String razaoSocial,
        String nomeFantasia,
        String telefone,
        String email,
        String cnpj,
        String endereco,
        String cidade,
        String cep
) {
}
