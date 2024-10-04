package com.antonio_victor.projetos.distribuidora.vendas.controller;

import com.antonio_victor.projetos.distribuidora.vendas.model.ComissaoDto;
import com.antonio_victor.projetos.distribuidora.vendas.model.DadosComissao;
import com.antonio_victor.projetos.distribuidora.vendas.service.ComissaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comissao")
public class ComissaoController {
    private final ComissaoService comissaoService;

    public ComissaoController(ComissaoService comissaoService) {
        this.comissaoService = comissaoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<DadosComissao> criarComissao(@RequestBody ComissaoDto comissaoDto, @RequestParam(name = "vendedor") String vendedor ) {
        var comissao = comissaoService.criarComissao(comissaoDto, vendedor);
        return ResponseEntity.ok(comissao);
    }

    @GetMapping
    public ResponseEntity<List<DadosComissao>> listarComissoes() {
        return ResponseEntity.ok(comissaoService.listarComissoes());
    }
}
