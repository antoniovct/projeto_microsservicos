package com.antonio_victor.projetos.distribuidora.vendas.Client;

import com.antonio_victor.projetos.distribuidora.vendas.model.ClienteDto;
import com.antonio_victor.projetos.distribuidora.vendas.model.VendedorDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Component
@FeignClient(name = "cadastros-ms")
public interface CadastrosClient {

    @GetMapping("vendedores/vendedor/{id}")
    Optional<VendedorDto> buscarVendedorPorId(@PathVariable("id") Long id);

    @GetMapping("clientes/cliente/{id}")
    Optional<ClienteDto> buscarClientePorId(@PathVariable("id") Long id);
}
