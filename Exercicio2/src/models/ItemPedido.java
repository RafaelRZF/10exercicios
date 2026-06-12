package models;

public class ItemPedido {
    private Produto produto; // Representa o produto_id
    private int quantidade;
    private double precoUnitario;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public ItemPedido(Produto produto, int quantity, double precoUnitario) {
        this.produto = produto;
        this.quantidade = quantity;
        this.precoUnitario = precoUnitario;
    }
}