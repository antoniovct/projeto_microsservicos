package com.antonio_victor.projetos.distribuidora.vendas.model;


public record ProdutoDto(

        String descricao,
        Double preco,
        Integer quantidade,
        Long idMarca
) {
}
