package org.example.model;

/**
 * Representa um ingrediente utilizado nos pratos do restaurante.
 * Um ingrediente pode ser usado em vários pratos via tabela Receitas.
 */
public class Ingrediente {

    private int    id;
    private String nome;
    private String unidadeMedida;

    public Ingrediente() {}

    public Ingrediente(int id, String nome, String unidadeMedida) {
        this.id            = id;
        this.nome          = nome;
        this.unidadeMedida = unidadeMedida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    @Override
    public String toString() {
        return "Ingrediente{id=" + id + ", nome='" + nome
                + "', unidadeMedida='" + unidadeMedida + "'}";
    }
}