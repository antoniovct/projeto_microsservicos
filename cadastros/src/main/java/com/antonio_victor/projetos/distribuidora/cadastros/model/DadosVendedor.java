package com.antonio_victor.projetos.distribuidora.cadastros.model;


/**
 * DTO for {@link Vendedor}
 */
public record DadosVendedor(Long id, String nome, String telefone, String email, String cpf, String endereco,
                            String cidade, String cep){
    public DadosVendedor(Vendedor vendedor) {
        this(vendedor.getId(), vendedor.getNome(), vendedor.getTelefone(), vendedor.getEmail(),
                vendedor.getCpf(), vendedor.getEndereco(), vendedor.getCidade(), vendedor.getCep());
    }
}