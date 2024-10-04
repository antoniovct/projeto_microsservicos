package com.antonio_victor.projetos.distribuidora.cadastros.model;

/**
 * DTO for {@link Vendedor}
 */
public record DtoVendedorAtualizacao(String telefone, String email, String endereco, String cidade,
                                     String cep){
}