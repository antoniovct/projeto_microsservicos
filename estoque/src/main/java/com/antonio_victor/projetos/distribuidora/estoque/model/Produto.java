package com.antonio_victor.projetos.distribuidora.estoque.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String descricao;
    private Double preco;
    private Integer quantidadeEstoque;
    private Boolean estoqueBaixo = Boolean.TRUE;
    private Long idMarca;

    public Produto(DtoProduto dtoProduto) {
        this.descricao = dtoProduto.descricao();
        this.preco = dtoProduto.preco();
        this.quantidadeEstoque = dtoProduto.quantidade();
        this.idMarca = dtoProduto.idMarca();
        if (dtoProduto.quantidade() > 10) {
            this.estoqueBaixo = Boolean.FALSE;
        }
    }

    public void atualizarProduto(DtoAtualizacaoProduto atualizacaoProduto) {
        if (atualizacaoProduto.descricao() != null) {
            this.descricao = atualizacaoProduto.descricao();
        }
        if (atualizacaoProduto.preco() != null) {
            this.preco = atualizacaoProduto.preco();
        }
        if (atualizacaoProduto.quantidadeEstoque() != null) {
            this.quantidadeEstoque = atualizacaoProduto.quantidadeEstoque();
        }
        if (atualizacaoProduto.estoqueBaixo() != null) {
            this.estoqueBaixo = atualizacaoProduto.estoqueBaixo();
        }
    }

    public void saida(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }
}
