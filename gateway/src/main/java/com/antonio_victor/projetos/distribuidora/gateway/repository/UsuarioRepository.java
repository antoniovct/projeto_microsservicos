package com.antonio_victor.projetos.distribuidora.gateway.repository;

import com.antonio_victor.projetos.distribuidora.gateway.model.Usuario;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;


public interface UsuarioRepository extends R2dbcRepository<Usuario, Long> {
    Mono<UserDetails> findByEmail(String email);
}
