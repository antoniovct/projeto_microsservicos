package com.antonio_victor.projetos.distribuidora.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String cep;

    public Cliente(ClienteDto clienteDto) {
        this.razaoSocial = clienteDto.razaoSocial();
        this.nomeFantasia = clienteDto.nomeFantasia();
        this.cnpj = clienteDto.cnpj();
        this.email = clienteDto.email();
        this.telefone = clienteDto.telefone();
        this.endereco = clienteDto.endereco();
        this.cidade = clienteDto.cidade();
        this.cep = clienteDto.cep();
    }

}