package com.antonio_victor.projetos.distribuidora.estoque.model;

public record DadosMarca(
        Long id,
        String nome,
        Double comissao
) {
    public DadosMarca(Marca marca) {
        this(marca.getId(), marca.getNome(), marca.getComissao());
    }
}
