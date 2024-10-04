package com.antonio_victor.projetos.distribuidora.estoque.model;

public record DadosProduto(
        Long id,
        String descricao,
        Double preco,
        Integer quantidadeEstoque,
        Boolean estoqueBaixo,
        Long idMarca
) {
    public DadosProduto(Produto produto) {
        this(produto.getId(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEstoque(), produto.getEstoqueBaixo(), produto.getIdMarca());
    }
}
