package org.example.models;

import java.time.LocalDateTime;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private LocalDateTime dataCadastro;

    public Aluno() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Aluno(String nome, String email, LocalDateTime dataCadastro) {
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }
}