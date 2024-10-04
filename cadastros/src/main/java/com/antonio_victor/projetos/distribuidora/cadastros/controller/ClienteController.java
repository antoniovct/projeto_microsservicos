package com.antonio_victor.projetos.distribuidora.cadastros.controller;

import com.antonio_victor.projetos.distribuidora.cadastros.model.DadosCliente;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoCliente;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoClienteAtualizacao;
import com.antonio_victor.projetos.distribuidora.cadastros.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<DadosCliente> cadastrar(@RequestBody @Valid DtoCliente dtoCliente) {
        var cliente = clienteService.cadastrarCliente(dtoCliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<Page<DadosCliente>> listar(Pageable pageable) {
        var clientes = clienteService.listarClientes(pageable);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<DadosCliente> buscar(@PathVariable Long id) {
        var cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<DadosCliente>> buscarPorNome(@RequestParam(name = "nome") String nome) {
        return ResponseEntity.ok(clienteService.buscarClientePorNome(nome));
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<DadosCliente> editar(@PathVariable Long id, @RequestBody DtoClienteAtualizacao atualizacao) {
        var cliente = clienteService.atualizarCliente(atualizacao, id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
