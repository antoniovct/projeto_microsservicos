package com.antonio_victor.projetos.distribuidora.estoque.controller;

import com.antonio_victor.projetos.distribuidora.estoque.model.DadosProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoAtualizacaoProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoProduto;
import com.antonio_victor.projetos.distribuidora.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("estoque/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<DadosProduto> cadastrarProduto(@RequestBody @Valid DtoProduto dtoProduto, UriComponentsBuilder uriBuilder) {
        var produto = produtoService.cadastrarProduto(dtoProduto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PostMapping("/produto/{id}/saida")
    public ResponseEntity<DadosProduto> saidaProduto(@PathVariable("id") Long id, @RequestParam(name = "quantidade") Integer quantidade) {
        return ResponseEntity.ok(produtoService.saidaDeProduto(id, quantidade));
    }

    @GetMapping
    public ResponseEntity<Page<DadosProduto>> listarProdutos(Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarProdutos(pageable));
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<DadosProduto> buscarProdutoPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @GetMapping("/produto")
    public ResponseEntity<List<DadosProduto>> buscarProdutoPorNome(@RequestParam(name = "name") String nome) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorNome(nome));
    }

    @GetMapping("/marcas/{idMarca}")
    public ResponseEntity<List<DadosProduto>> buscarProdutoPorMarca(@PathVariable("idMarca") Long idMarca) {
        return ResponseEntity.ok().body(produtoService.listarProdutosPorMarca(idMarca));
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<DadosProduto> atualizarProduto(@PathVariable("id") Long id, @RequestBody DtoAtualizacaoProduto atualizacaoProduto) {
        return ResponseEntity.ok(produtoService.editarProduto(id, atualizacaoProduto));
    }

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Void> removerProduto(@PathVariable("id") Long id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
