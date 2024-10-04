package com.antonio_victor.projetos.distribuidora.cadastros.controller;

import com.antonio_victor.projetos.distribuidora.cadastros.model.DadosVendedor;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoVendedor;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoVendedorAtualizacao;
import com.antonio_victor.projetos.distribuidora.cadastros.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<DadosVendedor> cadastrar(@RequestBody @Valid DtoVendedor dtoVendedor) {
       var vendedor = vendedorService.cadastrarVendedor(dtoVendedor);
       return ResponseEntity.ok(vendedor);
    }

    @GetMapping
    public ResponseEntity<Page<DadosVendedor>> listar(Pageable pageable) {
        var vendedores = vendedorService.listarVendedores(pageable);
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<DadosVendedor> buscar(@PathVariable Long id) {
        var vendedor = vendedorService.buscarVendedorPorId(id);
        return ResponseEntity.ok(vendedor);
    }

    @GetMapping("/vendedor")
    public ResponseEntity<List<DadosVendedor>> buscarVendedorPorNome(@RequestParam(name = "nome") String nome) {
        return ResponseEntity.ok(vendedorService.buscarVendedorPorNome(nome));
    }

    @PutMapping("/vendedor/{id}")
    public ResponseEntity<DadosVendedor> editar(@PathVariable Long id, @RequestBody DtoVendedorAtualizacao atualizacao) {
        var vendedor = vendedorService.atualizarVendedor(atualizacao, id);
        return ResponseEntity.ok(vendedor);
    }

    @DeleteMapping("/vendedor/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        vendedorService.excluirVendedor(id);
        return ResponseEntity.noContent().build();
    }
}
