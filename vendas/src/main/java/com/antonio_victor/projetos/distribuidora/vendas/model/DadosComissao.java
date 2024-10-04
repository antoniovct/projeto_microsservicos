package com.antonio_victor.projetos.distribuidora.vendas.model;

/**
 * DTO for {@link Comissao}
 */
public record DadosComissao(Long id, String vendedor, String periodoApurado, Double valorTotalVendido,
                            Double valorComissao) {
  public DadosComissao(Comissao comissao) {
    this(comissao.getId(), comissao.getVendedor(), comissao.getPeriodoApurado(), comissao.getValorTotalVendido(), comissao.getValorComissao());
  }
}