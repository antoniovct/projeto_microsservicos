package com.antonio_victor.projetos.distribuidora.estoque.service;

import com.antonio_victor.projetos.distribuidora.estoque.model.DadosProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoAtualizacaoProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.DtoProduto;
import com.antonio_victor.projetos.distribuidora.estoque.model.Produto;
import com.antonio_victor.projetos.distribuidora.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public DadosProduto cadastrarProduto(DtoProduto dtoProduto) {
        Produto produto = new Produto(dtoProduto);
        produtoRepository.save(produto);
        return new DadosProduto(produto);
    }

    public Page<DadosProduto> listarProdutos(Pageable pageable  ) {
        return produtoRepository.findAll(pageable).map(DadosProduto::new);
    }

    public List<DadosProduto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findAllByDescricaoContainingIgnoreCase(nome).stream().map(DadosProduto::new).toList();

    }

    public DadosProduto buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.getReferenceById(id);
        return new DadosProduto(produto);
    }

    @Transactional
    public DadosProduto editarProduto(Long id, DtoAtualizacaoProduto atualizacaoProduto) {
        Produto produto = produtoRepository.getReferenceById(id);
        produto.atualizarProduto(atualizacaoProduto);
        return new DadosProduto(produto);
    }

    @Transactional
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    @Transactional
    public DadosProduto saidaDeProduto(Long id, Integer quantidade) {
        Produto produto = produtoRepository.getReferenceById(id);
        produto.saida(quantidade);
        return new DadosProduto(produto);
    }

    public List<DadosProduto> listarProdutosPorMarca(Long idMarca) {
        List<Produto> produtos = produtoRepository.findAllByIdMarca(idMarca);
        return produtos.stream().map(DadosProduto::new).toList();
    }
}
