package com.antonio_victor.projetos.distribuidora.gateway.controller;

import com.antonio_victor.projetos.distribuidora.gateway.model.DadosUsuario;
import com.antonio_victor.projetos.distribuidora.gateway.model.DtoUsuario;
import com.antonio_victor.projetos.distribuidora.gateway.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("usuarios")
public class UserController {
    private final UsuarioService usuarioService;

    public UserController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public Mono<ResponseEntity<DadosUsuario>> cadastrar(@RequestBody @Valid DtoUsuario dtoUsuario) {
        return usuarioService.cadastrarUsuario(dtoUsuario, dtoUsuario.senha())
                .map(usuario -> ResponseEntity.ok(usuario))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping
    public Flux<DadosUsuario> listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        return usuarios;
    }
}
