package com.antonio_victor.projetos.distribuidora.cadastros.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    private String cnpj;
    private String endereco;
    private String cidade;
    private String cep;

    public Cliente(DtoCliente dtoCliente) {
        this.razaoSocial = dtoCliente.razaoSocial();
        this.nomeFantasia = dtoCliente.nomeFantasia();
        this.telefone = dtoCliente.telefone();
        this.email = dtoCliente.email();
        this.cnpj = dtoCliente.cnpj();
        this.endereco = dtoCliente.endereco();
        this.cidade = dtoCliente.cidade();
        this.cep = dtoCliente.cep();
    }

    public void atualizarCliente(DtoClienteAtualizacao atualizacao) {
        if (atualizacao.email() != null) {
            this.email = atualizacao.email();
        } else if (atualizacao.nomeFantasia() != null) {
            this.nomeFantasia = atualizacao.nomeFantasia();
        } else if (atualizacao.telefone() != null) {
            this.telefone = atualizacao.telefone();
        } else if (atualizacao.endereco() != null) {
            this.endereco = atualizacao.endereco();
        } else if (atualizacao.cidade() != null) {
            this.cidade = atualizacao.cidade();
        } else if (atualizacao.cep() != null) {
            this.cep = atualizacao.cep();
        }
    }

}
