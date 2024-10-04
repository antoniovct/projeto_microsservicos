package com.antonio_victor.projetos.distribuidora.estoque.model;

public record DtoAtualizacaoProduto(
        String descricao,
        Double preco,
        Integer quantidadeEstoque,
        Boolean estoqueBaixo

) {
}
