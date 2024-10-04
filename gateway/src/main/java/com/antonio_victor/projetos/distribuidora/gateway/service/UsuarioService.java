package com.antonio_victor.projetos.distribuidora.gateway.service;

import com.antonio_victor.projetos.distribuidora.gateway.model.DadosUsuario;
import com.antonio_victor.projetos.distribuidora.gateway.model.DtoUsuario;
import com.antonio_victor.projetos.distribuidora.gateway.model.Usuario;
import com.antonio_victor.projetos.distribuidora.gateway.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Mono<DadosUsuario> cadastrarUsuario(DtoUsuario dtoUsuario, String senha) {
        String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt());
        Usuario usuario = new Usuario(dtoUsuario, senhaHash);
        return usuarioRepository.save(usuario).map(DadosUsuario::new);
    }

    public Flux<DadosUsuario> listarUsuarios() {
        return usuarioRepository.findAll().map(DadosUsuario::new);
    }
}
