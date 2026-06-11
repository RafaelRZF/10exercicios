package org.example.model;

import java.time.LocalDate;

/**
 * Representa um paciente da clínica médica.
 * Um paciente pode ter várias consultas agendadas.
 */
public class Paciente {

    private int       id;
    private String    nome;
    private LocalDate dataNascimento;
    private String    telefone;
    private String    endereco;

    public Paciente() {}

    public Paciente(int id, String nome, LocalDate dataNascimento,
                    String telefone, String endereco) {
        this.id             = id;
        this.nome           = nome;
        this.dataNascimento = dataNascimento;
        this.telefone       = telefone;
        this.endereco       = endereco;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
        return "Paciente{id=" + id + ", nome='" + nome + "', dataNascimento="
                + dataNascimento + ", telefone='" + telefone + "'}";
    }
}