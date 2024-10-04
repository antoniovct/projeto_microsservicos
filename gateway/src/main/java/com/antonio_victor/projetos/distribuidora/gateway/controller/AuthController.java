package com.antonio_victor.projetos.distribuidora.gateway.controller;

import com.antonio_victor.projetos.distribuidora.gateway.model.DtoUsuario;
import com.antonio_victor.projetos.distribuidora.gateway.model.Usuario;
import com.antonio_victor.projetos.distribuidora.gateway.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("authenticate")
public class AuthController {
    private final AuthService authService;
    private final ReactiveAuthenticationManager authenticationManager;

    public AuthController(AuthService authService, ReactiveAuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public Mono<ResponseEntity> authenticate(@RequestBody DtoUsuario dtoUsuario) {
        var token = new UsernamePasswordAuthenticationToken(dtoUsuario.email(), dtoUsuario.senha());
        var user = authenticationManager.authenticate(token);
        return user
                .flatMap(authentication -> authService.autenticar((Usuario) authentication.getPrincipal()))
                .map(tokens -> ResponseEntity.ok().body(tokens));
    }
}
