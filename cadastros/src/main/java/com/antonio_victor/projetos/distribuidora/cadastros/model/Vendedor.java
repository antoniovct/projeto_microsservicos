package com.antonio_victor.projetos.distribuidora.cadastros.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "vendedor")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String endereco;
    private String cidade;
    private String cep;

    public Vendedor(DtoVendedor dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.cpf = dto.cpf();
        this.endereco = dto.endereco();
        this.cidade = dto.cidade();
        this.cep = dto.cep();
    }

    public void atualizarVendedor(DtoVendedorAtualizacao atualizacao) {
        if (atualizacao.email() != null) {
            this.email = atualizacao.email();
        } else if (atualizacao.telefone() != null) {
            this.telefone = atualizacao.telefone();
        } else if (atualizacao.endereco() != null) {
            this.endereco = atualizacao.endereco();
        } else if (atualizacao.cep() != null) {
            this.cep = atualizacao.cep();
        } else if (atualizacao.cidade() != null) {
            this.cidade = atualizacao.cidade();
        }
    }

}
