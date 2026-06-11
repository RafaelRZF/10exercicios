package org.example.models;

import java.time.LocalDateTime;

public class Comentarios {
    private int id;
    private String autor;
    private String conteudo;
    private LocalDateTime dataComentario;
    private Posts postID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(LocalDateTime dataComentario) {
        this.dataComentario = dataComentario;
    }

    public Posts getPostID() {
        return postID;
    }

    public void setPostID(Posts postID) {
        this.postID = postID;
    }

    public Comentarios(String autor, String conteudo, LocalDateTime dataComentario, Posts postID) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataComentario = dataComentario;
        this.postID = new Posts();
    }

    public Comentarios(){

    }
}
