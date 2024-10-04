package com.antonio_victor.projetos.distribuidora.cadastros.service;

import com.antonio_victor.projetos.distribuidora.cadastros.model.Cliente;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DadosCliente;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoCliente;
import com.antonio_victor.projetos.distribuidora.cadastros.model.DtoClienteAtualizacao;
import com.antonio_victor.projetos.distribuidora.cadastros.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public DadosCliente cadastrarCliente(DtoCliente dtoCliente) {
        var cliente = new Cliente(dtoCliente);
        clienteRepository.save(cliente);
        return new DadosCliente(cliente);
    }

    public Page<DadosCliente> listarClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(DadosCliente::new);
    }

    public DadosCliente buscarClientePorId(Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        return new DadosCliente(cliente);
    }

    public List<DadosCliente> buscarClientePorNome(String nome) {
        if (!clienteRepository.findAllByRazaoSocialContainingIgnoreCase(nome).isEmpty()) {
            return clienteRepository.findAllByRazaoSocialContainingIgnoreCase(nome).stream().map(DadosCliente::new).toList();
        } else if (!clienteRepository.findAllByNomeFantasiaContainingIgnoreCase(nome).isEmpty()) {
            return clienteRepository.findAllByNomeFantasiaContainingIgnoreCase(nome).stream().map(DadosCliente::new).toList();
        }
        throw new EntityNotFoundException("Cliente não encontrado!");
    }

    @Transactional
    public DadosCliente atualizarCliente(DtoClienteAtualizacao atualizacao, Long id) {
        var cliente = clienteRepository.getReferenceById(id);
        cliente.atualizarCliente(atualizacao);
        return new DadosCliente(cliente);
    }

    @Transactional
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
