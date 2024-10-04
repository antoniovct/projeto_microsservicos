package com.antonio_victor.projetos.distribuidora.estoque.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "marcas")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Marca {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Setter(AccessLevel.NONE)
        private Long id;
        private String nome;
        private Double comissao;

        public Marca(DtoMarca dtoMarca){
            this.nome = dtoMarca.nome();
            this.comissao = dtoMarca.comissao();
        }

        public void atualizar(DtoAtualizacaoMarca dtoAtualizacaoMarca){
            if(dtoAtualizacaoMarca.nome() != null) {
                this.nome = dtoAtualizacaoMarca.nome();
            } else if (dtoAtualizacaoMarca.comissao() != null) {
                this.comissao = dtoAtualizacaoMarca.comissao();
            }
        }
}
