package org.example.model;

import java.time.LocalDateTime;

/**
 * Representa uma movimentação de estoque (entrada ou saída) de um produto.
 * O campo tipoTransacao deve conter "entrada" ou "saida".
 */
public class TransacaoEstoque {

    private int           id;
    private int           produtoId;
    private String        tipoTransacao;
    private int           quantidade;
    private LocalDateTime dataTransacao;

    public TransacaoEstoque() {}

    public TransacaoEstoque(int id, int produtoId, String tipoTransacao,
                            int quantidade, LocalDateTime dataTransacao) {
        this.id            = id;
        this.produtoId     = produtoId;
        this.tipoTransacao = tipoTransacao;
        this.quantidade    = quantidade;
        this.dataTransacao = dataTransacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public String getTipoTransacao() { return tipoTransacao; }
    public void setTipoTransacao(String t) { this.tipoTransacao = t; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public LocalDateTime getDataTransacao() { return dataTransacao; }
    public void setDataTransacao(LocalDateTime d) { this.dataTransacao = d; }

    @Override
    public String toString() {
        return "TransacaoEstoque{id=" + id + ", produtoId=" + produtoId
                + ", tipo='" + tipoTransacao + "', quantidade=" + quantidade
                + ", data=" + dataTransacao + "}";
    }
}