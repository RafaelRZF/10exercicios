package org.example.model;

/**
 * Representa um produto no inventário da loja.
 * Cada produto está associado a um fornecedor via fornecedor_id.
 */
public class Produto {

    private int    id;
    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int    fornecedorId;

    public Produto() {}

    public Produto(int id, String nome, String descricao,
                   double precoCompra, double precoVenda, int fornecedorId) {
        this.id           = id;
        this.nome         = nome;
        this.descricao    = descricao;
        this.precoCompra  = precoCompra;
        this.precoVenda   = precoVenda;
        this.fornecedorId = fornecedorId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPrecoCompra() { return precoCompra; }
    public void setPrecoCompra(double precoCompra) { this.precoCompra = precoCompra; }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public int getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(int fornecedorId) { this.fornecedorId = fornecedorId; }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', precoVenda="
                + precoVenda + ", fornecedorId=" + fornecedorId + "}";
    }
}