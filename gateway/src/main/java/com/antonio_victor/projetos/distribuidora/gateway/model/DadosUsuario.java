package com.antonio_victor.projetos.distribuidora.gateway.model;


public record DadosUsuario(
        Long id,
        String email,
        String senha
) {
    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
