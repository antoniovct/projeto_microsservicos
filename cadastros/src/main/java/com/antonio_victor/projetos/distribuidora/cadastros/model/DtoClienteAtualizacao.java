package com.antonio_victor.projetos.distribuidora.cadastros.model;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
public record DtoClienteAtualizacao(String nomeFantasia, String telefone, String email, String endereco, String cidade,
                                    String cep) {
}