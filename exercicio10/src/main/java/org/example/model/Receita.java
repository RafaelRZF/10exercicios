package org.example.model;

/**
 * Representa a relação entre um prato e seus ingredientes.
 * Tabela associativa que resolve o relacionamento muitos-para-muitos
 * entre Pratos e Ingredientes, armazenando também a quantidade necessária.
 */
public class Receita {

    private int    pratoId;
    private int    ingredienteId;
    private double quantidadeNecessaria;

    public Receita() {}

    public Receita(int pratoId, int ingredienteId, double quantidadeNecessaria) {
        this.pratoId              = pratoId;
        this.ingredienteId        = ingredienteId;
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    public int getPratoId() { return pratoId; }
    public void setPratoId(int pratoId) { this.pratoId = pratoId; }

    public int getIngredienteId() { return ingredienteId; }
    public void setIngredienteId(int ingredienteId) { this.ingredienteId = ingredienteId; }

    public double getQuantidadeNecessaria() { return quantidadeNecessaria; }
    public void setQuantidadeNecessaria(double quantidadeNecessaria) {
        this.quantidadeNecessaria = quantidadeNecessaria;
    }

    @Override
    public String toString() {
        return "Receita{pratoId=" + pratoId + ", ingredienteId=" + ingredienteId
                + ", quantidade=" + quantidadeNecessaria + "}";
    }
}