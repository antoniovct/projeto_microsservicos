package com.antonio_victor.projetos.distribuidora.gateway.service;

import com.antonio_victor.projetos.distribuidora.gateway.model.Usuario;
import com.antonio_victor.projetos.distribuidora.gateway.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthService implements ReactiveUserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final JWTService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JWTService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }


    public Mono<String> autenticar(Usuario usuario) {
        return Mono.just(jwtService.createToken(usuario));
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return usuarioRepository.findByEmail(username);
    }
}
