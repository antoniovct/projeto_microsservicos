package com.antonio_victor.projetos.distribuidora.vendas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    private String descricao;
    private Double preco;
    private Long idMarca;
    private Integer quantidade;
    private Double valorTotal;

    public ItemPedido(ProdutoDto produtoDto) {
        this.descricao = produtoDto.descricao();
        this.preco = produtoDto.preco();
        this.idMarca = produtoDto.idMarca();
    }


}
