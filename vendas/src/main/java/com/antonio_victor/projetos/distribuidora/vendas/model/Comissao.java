package com.antonio_victor.projetos.distribuidora.vendas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comissao")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Comissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendedor;
    private String periodoApurado;
    private Double valorTotalVendido;
    private Double valorComissao = 0.0;
    @ManyToMany
    private List<Pedido> pedidos = new ArrayList<>();


    public Comissao(ComissaoDto comissaoDto, String vendedor, Double valorTotalVendido,List<Pedido> pedidos ) {
        var dataInicial = LocalDate.parse(comissaoDto.dataInicial(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        var dataFinal = LocalDate.parse(comissaoDto.dataFinal(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.periodoApurado = Period.between(dataInicial,dataFinal).toString();
        this.vendedor = vendedor;
        this.valorTotalVendido = valorTotalVendido;
        this.pedidos.addAll(pedidos);
    }

    public void adicionarComissao(Double comissao) {
        valorComissao += comissao;
    }
}
