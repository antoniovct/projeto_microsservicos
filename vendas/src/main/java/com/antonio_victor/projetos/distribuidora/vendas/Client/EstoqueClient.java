package com.antonio_victor.projetos.distribuidora.vendas.Client;

import com.antonio_victor.projetos.distribuidora.vendas.model.MarcaDto;
import com.antonio_victor.projetos.distribuidora.vendas.model.ProdutoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Component
@FeignClient(name = "estoque-ms")
public interface EstoqueClient {
    @PostMapping("estoque/produtos/produto/{id}/saida" )
    Optional<ProdutoDto> buscarProduto(@PathVariable("id") Long id, @RequestParam(name = "quantidade") Integer quantidade);

    @GetMapping("estoque/marcas/marca/{id}")
    Optional<MarcaDto> buscarMarca(@PathVariable("id") Long id);
}
