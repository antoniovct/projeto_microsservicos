package com.antonio_victor.projetos.distribuidora.vendas.service;

import com.antonio_victor.projetos.distribuidora.vendas.Client.EstoqueClient;
import com.antonio_victor.projetos.distribuidora.vendas.model.*;
import com.antonio_victor.projetos.distribuidora.vendas.repository.ComissaoRepository;
import com.antonio_victor.projetos.distribuidora.vendas.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComissaoService {
    private final ComissaoRepository comissaoRepository;
    private final PedidoRepository pedidoRepository;
    private final EstoqueClient estoqueClient;

    public ComissaoService(ComissaoRepository comissaoRepository, PedidoRepository pedidoRepository, EstoqueClient estoqueClient) {
        this.comissaoRepository = comissaoRepository;
        this.pedidoRepository = pedidoRepository;
        this.estoqueClient = estoqueClient;
    }

    @Transactional
    public DadosComissao criarComissao(ComissaoDto comissaoDto, String vendedor) {
        List<Pedido> pedidos = pedidoRepository.findAllByVendedor(vendedor);
        var valorTotal = pedidos.stream().map(Pedido::getValorTotal).reduce(0.0, Double::sum);
        Comissao comissao = new Comissao(comissaoDto,vendedor,valorTotal,pedidos);
        List<ItemPedido> produtos = pedidos.stream().map(Pedido::getProduto).flatMap(Collection::stream).toList();
        for(ItemPedido item : produtos){
            var marcaDto = estoqueClient.buscarMarca(item.getIdMarca());
            if (marcaDto.isPresent()){
                var marca = marcaDto.get();
                var comissaoMarca = item.getValorTotal() * (marca.comissao() / 100);
                comissao.adicionarComissao(comissaoMarca);
            }
        }
        comissaoRepository.save(comissao);
        return new DadosComissao(comissao);
    }

    public List<DadosComissao> listarComissoes() {
        return comissaoRepository.findAll().stream().map(DadosComissao::new).toList();
    }
}
