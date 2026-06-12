package org.example.model;

import java.time.LocalDate;

/**
 * Representa a avaliação de um filme feita por um usuário.
 * Um usuário pode avaliar vários filmes e um filme pode ter várias avaliações.
 */
public class Avaliacao {

    private int       id;
    private int       usuarioId;
    private int       filmeId;
    private int       nota;        // nota de 1 a 10
    private String    comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao() {}

    public Avaliacao(int id, int usuarioId, int filmeId,
                     int nota, String comentario, LocalDate dataAvaliacao) {
        this.id            = id;
        this.usuarioId     = usuarioId;
        this.filmeId       = filmeId;
        this.nota          = nota;
        this.comentario    = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getFilmeId() { return filmeId; }
    public void setFilmeId(int filmeId) { this.filmeId = filmeId; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getDataAvaliacao() { return dataAvaliacao; }
    public void setDataAvaliacao(LocalDate dataAvaliacao) { this.dataAvaliacao = dataAvaliacao; }

    @Override
    public String toString() {
        return "Avaliacao{id=" + id + ", usuarioId=" + usuarioId + ", filmeId=" + filmeId
                + ", nota=" + nota + ", data=" + dataAvaliacao + "}";
    }
}