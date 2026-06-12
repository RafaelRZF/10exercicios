package org.example.models;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;

    public Autor(String nome, String nacionalidade){
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }

    public Autor(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
}
