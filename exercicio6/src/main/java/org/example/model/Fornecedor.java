package org.example.model;

/**
 * Representa um fornecedor da loja.
 * Cada fornecedor pode abastecer vários produtos.
 */
public class Fornecedor {

    private int    id;
    private String nome;
    private String contato;
    private String telefone;

    public Fornecedor() {}

    public Fornecedor(int id, String nome, String contato, String telefone) {
        this.id       = id;
        this.nome     = nome;
        this.contato  = contato;
        this.telefone = telefone;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return "Fornecedor{id=" + id + ", nome='" + nome + "', contato='"
                + contato + "', telefone='" + telefone + "'}";
    }
}