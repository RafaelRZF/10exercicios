package models;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int QTDEstoque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQTDEstoque() {
        return QTDEstoque;
    }

    public void setQTDEstoque(int QTDEstoque) {
        this.QTDEstoque = QTDEstoque;
    }

    public Produto(String nome, double preco, int QTDEstoque){
        this.nome = nome;
        this.preco = preco;
        this.QTDEstoque = QTDEstoque;
    }
}
