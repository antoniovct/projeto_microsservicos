package com.antonio_victor.projetos.distribuidora.estoque.controller;

import com.antonio_victor.projetos.distribuidora.estoque.model.DadosMarca;
import com.antonio_victor.projetos.distribuidora.estoque.model.DadosProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoAtualizacaoMarca;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoMarca;
import com.antonio_victor.projetos.distribuidora.estoque.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;


@RestController
@RequestMapping("estoque/marcas")
public class MarcaController {
    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<DadosMarca> cadastrarMarca(@RequestBody @Valid DtoMarca dtoMarca, UriComponentsBuilder uriBuilder) {
        var marca = marcaService.cadastrarMarca(dtoMarca);
        var uri = uriBuilder.path("/marca/{id}").buildAndExpand(marca.id()).toUri();
        return ResponseEntity.created(uri).body(marca);
    }

    @GetMapping
    public ResponseEntity<Page<DadosMarca>> listarMarcas(Pageable pageable) {
        return ResponseEntity.ok(marcaService.listarMarcas(pageable));
    }

    @GetMapping("/marca")
    public ResponseEntity<List<DadosMarca>> buscarMarcaPorNome(@RequestParam(name = "name") String nome) {
        return ResponseEntity.ok(marcaService.buscarMarcaPorNome(nome));
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<DadosMarca> buscarMarcaPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(marcaService.buscarMarcaPorId(id));
    }

    @PutMapping("/marca/{id}")
    public ResponseEntity<DadosMarca> atualizarMarca(@PathVariable("id") Long id, @RequestBody DtoAtualizacaoMarca dtoMarca) {
        return ResponseEntity.ok(marcaService.atualizarMarca(id, dtoMarca));
    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity<Void> excluirMarca(@PathVariable("id") Long id) {
        marcaService.removerMarca(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/produtos")
    public ResponseEntity<List<DadosProduto>> buscarProdutosPorMarca(@PathVariable("id") Long idMarca) {
        return ResponseEntity.ok(marcaService.buscarProdutoPorMarca(idMarca));
    }

}
