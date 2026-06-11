package models;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private Projeto projeto;   // Associação com a classe Projeto
    private Usuario usuario;   // Associação com a classe Usuario
    private String descricao;
    private String status;
    private LocalDate dataVencimento;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Projeto getProjeto() { return projeto; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public Tarefa(Projeto projeto, Usuario usuario, String descricao, String status, LocalDate dataVencimento) {
        this.projeto = projeto;
        this.usuario = usuario;
        this.descricao = descricao;
        this.status = status;
        this.dataVencimento = dataVencimento;
    }
}