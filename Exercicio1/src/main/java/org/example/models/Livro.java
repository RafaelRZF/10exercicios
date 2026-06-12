package org.example.models;

public class Livro {
    private int id;
    private String titulo;
    private int anoPublicacao;
    private Autor autor_id;
    private Editora editora_id;

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public Autor getAutor_id() {
        return autor_id;
    }

    public Editora getEditora_id() {
        return editora_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setAutor_id(Autor autor_id) {
        this.autor_id = autor_id;
    }

    public void setEditora_id(Editora editora_id) {
        this.editora_id = editora_id;
    }

    public Livro(String titulo, int anoPublicacao, Autor autor_id, Editora editora_id){
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor_id = autor_id;
        this.editora_id = editora_id;
    }

    public Livro(){

    }
}