package org.example.model;

/**
 * Representa um filme disponível na plataforma de streaming.
 * Um filme pode ser avaliado por vários usuários.
 */
public class Filme {

    private int    id;
    private String titulo;
    private int    anoLancamento;
    private String diretor;
    private String genero;

    public Filme() {}

    public Filme(int id, String titulo, int anoLancamento,
                 String diretor, String genero) {
        this.id             = id;
        this.titulo         = titulo;
        this.anoLancamento  = anoLancamento;
        this.diretor        = diretor;
        this.genero         = genero;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }

    public String getDiretor() { return diretor; }
    public void setDiretor(String diretor) { this.diretor = diretor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    @Override
    public String toString() {
        return "Filme{id=" + id + ", titulo='" + titulo + "', ano=" + anoLancamento
                + ", diretor='" + diretor + "', genero='" + genero + "'}";
    }
}