package com.antonio_victor.projetos.distribuidora.vendas.controller;

import com.antonio_victor.projetos.distribuidora.vendas.model.DadosPedido;
import com.antonio_victor.projetos.distribuidora.vendas.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarPedido(@RequestParam(name = "idVendedor") Long idVendedor, @RequestParam(name = "idCliente") Long idCliente) {
        var response = pedidoService.criarPedido(idVendedor, idCliente);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/pedido/{idPedido}/produtos/adicionar")
    public ResponseEntity<Void> adicionarProduto(@PathVariable Long idPedido, @RequestParam(name = "idProduto") Long idProduto, @RequestParam(name = "quantidade") int quantidade) {
        pedidoService.adicionarProduto(idPedido, idProduto, quantidade);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<DadosPedido>> listarPedidos(Pageable pageable) {
        return ResponseEntity.ok(pedidoService.listarPedidos(pageable));
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<DadosPedido> buscarPedido(@PathVariable Long id) {
        var pedido = pedidoService.buscarPedido(id);
        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping("/pedido/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }
}
