package com.antonio_victor.projetos.distribuidora.cadastros.model;

import java.io.Serializable;

/**
 * DTO for {@link Cliente}
 */
public record DadosCliente(Long id, String razaoSocial, String nomeFantasia, String telefone, String email, String cnpj,
                           String endereco, String cidade, String cep){
  public DadosCliente(Cliente cliente) {
    this(cliente.getId(), cliente.getRazaoSocial(), cliente.getNomeFantasia(), cliente.getTelefone()
    , cliente.getEmail(), cliente.getCnpj(), cliente.getEndereco(), cliente.getCidade(), cliente.getCep());
  }
}