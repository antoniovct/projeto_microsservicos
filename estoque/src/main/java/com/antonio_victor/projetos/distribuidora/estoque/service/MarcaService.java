package com.antonio_victor.projetos.distribuidora.estoque.service;

import com.antonio_victor.projetos.distribuidora.estoque.model.*;
import com.antonio_victor.projetos.distribuidora.estoque.repository.MarcaRepository;
import com.antonio_victor.projetos.distribuidora.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;
    private final ProdutoRepository produtoRepository;

    public MarcaService(MarcaRepository marcaRepository, ProdutoRepository produtoRepository) {
        this.marcaRepository = marcaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public DadosMarca cadastrarMarca(DtoMarca dtoMarca) {
        Marca marca = new Marca(dtoMarca);
        marcaRepository.save(marca);
        return new DadosMarca(marca);
    }

    public Page<DadosMarca> listarMarcas(Pageable pageable) {
        return marcaRepository.findAll(pageable).map(DadosMarca::new);
    }

    public DadosMarca buscarMarcaPorId(Long id) {
        Marca marca = marcaRepository.getReferenceById(id);
        return new DadosMarca(marca);
    }

    public List<DadosMarca> buscarMarcaPorNome(String nome) {
        return marcaRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(DadosMarca::new).toList();
    }

    @Transactional
    public DadosMarca atualizarMarca(Long id, DtoAtualizacaoMarca dtoMarca) {
        Marca marca = marcaRepository.getReferenceById(id);
        marca.atualizar(dtoMarca);
        return new DadosMarca(marca);
    }

    @Transactional
    public void removerMarca(Long id) {
        marcaRepository.deleteById(id);
    }

    public List<DadosProduto> buscarProdutoPorMarca(Long idMarca) {
        return produtoRepository.findAllByIdMarca(idMarca).stream().map(DadosProduto::new).toList();
    }


}
