package com.antonio_victor.projetos.distribuidora.vendas.service;

import com.antonio_victor.projetos.distribuidora.vendas.Client.CadastrosClient;
import com.antonio_victor.projetos.distribuidora.vendas.Client.EstoqueClient;
import com.antonio_victor.projetos.distribuidora.vendas.model.*;
import com.antonio_victor.projetos.distribuidora.vendas.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final EstoqueClient estoqueClient;
    private final CadastrosClient cadastrosClient;

    public PedidoService(PedidoRepository pedidoRepository, EstoqueClient estoqueClient, CadastrosClient cadastrosClient) {
        this.pedidoRepository = pedidoRepository;
        this.estoqueClient = estoqueClient;
        this.cadastrosClient = cadastrosClient;
    }
    @Transactional
    public String criarPedido(Long idVendedor, Long idCliente) {
        var vendedor = cadastrosClient.buscarVendedorPorId(idVendedor);
        var cliente = cadastrosClient.buscarClientePorId(idCliente);

        if (vendedor.isPresent() && cliente.isPresent()) {
            var vendedorDto = vendedor.get();
            var clienteDto = cliente.get();
            Cliente novoCliente = new Cliente(clienteDto);
            Pedido pedido = new Pedido(vendedorDto, novoCliente);
            pedidoRepository.save(pedido);
            return "Pedido criado com sucesso! ID: " + pedido.getId();
        }else {
            throw new RuntimeException("Cliente ou Vendedor não encontrado!");
        }

    }

    @Transactional
    public void adicionarProduto(Long idPedido,Long idProduto, int quantidade) {
        var produto = estoqueClient.buscarProduto(idProduto, quantidade);

        if (produto.isPresent()) {
            var produtoDto = produto.get();
            ItemPedido itemPedido = new ItemPedido(produtoDto);
            Pedido pedido = pedidoRepository.getReferenceById(idPedido);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setValorTotal(itemPedido.getPreco()*itemPedido.getQuantidade());
            pedido.addProduto(itemPedido);
        }else {
            throw new RuntimeException("Produto não encontrado no estoque");
        }

    }

    public Page<DadosPedido> listarPedidos(Pageable pageable) {
        return pedidoRepository.findAll(pageable).map(DadosPedido::new);
    }

    public DadosPedido buscarPedido(Long idPedido) {
        var pedido = pedidoRepository.getReferenceById(idPedido);
        return new DadosPedido(pedido);
    }

    @Transactional
    public void excluirPedido(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
    }
}
