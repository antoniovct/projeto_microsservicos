package com.antonio_victor.projetos.distribuidora.vendas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    private String data;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private String vendedor;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "produtos_pedido")
    private List<ItemPedido> produto = new ArrayList<>();
    private Double valorTotal = 0.0;
    public Pedido(VendedorDto vendedorDto, Cliente cliente) {
        this.data = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.vendedor = vendedorDto.nome();
        this.cliente = cliente;
    }

    public void addProduto(ItemPedido itemPedido) {
        this.produto.add(itemPedido);
        this.valorTotal += itemPedido.getValorTotal();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
