package com.antonio_victor.projetos.distribuidora.cadastros.service;

import com.antonio_victor.projetos.distribuidora.cadastros.model.DadosVendedor;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoVendedor;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoVendedorAtualizacao;
import com.antonio_victor.projetos.distribuidora.cadastros.model.Vendedor;
import com.antonio_victor.projetos.distribuidora.cadastros.repository.VendedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Transactional
    public DadosVendedor cadastrarVendedor(DtoVendedor vendedorDto) {
        Vendedor vendedor = new Vendedor(vendedorDto);
        vendedorRepository.save(vendedor);
        return new DadosVendedor(vendedor);
    }

    public Page<DadosVendedor> listarVendedores(Pageable pageable) {
        return vendedorRepository.findAll(pageable).map(DadosVendedor::new);
    }

    public DadosVendedor buscarVendedorPorId(Long id) {
        Vendedor vendedor = vendedorRepository.getReferenceById(id);
        return new DadosVendedor(vendedor);
    }

    public List<DadosVendedor> buscarVendedorPorNome(String nome) {
        return vendedorRepository.findAllByNomeContainingIgnoreCase(nome).stream().map(DadosVendedor::new).toList();

    }

    @Transactional
    public DadosVendedor atualizarVendedor(DtoVendedorAtualizacao atualizacao, Long id) {
        var vendedor = vendedorRepository.getReferenceById(id);
        vendedor.atualizarVendedor(atualizacao);
        return new DadosVendedor(vendedor);
    }

    @Transactional
    public void excluirVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}
