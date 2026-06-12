package org.example.model;

import java.time.LocalDate;

/**
 * Representa um evento organizado pela instituição.
 * Um evento pode ter vários participantes inscritos.
 */
public class Evento {

    private int       id;
    private String    nome;
    private LocalDate dataEvento;
    private String    local;
    private String    descricao;

    public Evento() {}

    public Evento(int id, String nome, LocalDate dataEvento, String local, String descricao) {
        this.id         = id;
        this.nome       = nome;
        this.dataEvento = dataEvento;
        this.local      = local;
        this.descricao  = descricao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "Evento{id=" + id + ", nome='" + nome + "', data=" + dataEvento
                + ", local='" + local + "'}";
    }
}