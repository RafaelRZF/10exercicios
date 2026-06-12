package org.example;

import org.example.dao.IngredienteDao;
import org.example.dao.PratoDao;
import org.example.dao.ReceitaDao;
import org.example.model.Ingrediente;
import org.example.model.Prato;
import org.example.model.Receita;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe principal — demonstra o funcionamento do sistema de gerenciamento de restaurante.
 */
public class Main {

    public static void main(String[] args) {

        PratoDao       pratoDao       = new PratoDao();
        IngredienteDao ingredienteDao = new IngredienteDao();
        ReceitaDao     receitaDao     = new ReceitaDao();

        try {
            // Inserindo pratos
            pratoDao.inserir(new Prato(0, "Macarrão à Bolonhesa",
                    "Macarrão com molho de carne moída", 35.90));
            pratoDao.inserir(new Prato(0, "Frango Grelhado",
                    "Frango grelhado com legumes", 42.50));
            pratoDao.inserir(new Prato(0, "Salada Caesar",
                    "Salada com alface, croutons e molho caesar", 28.00));
            System.out.println("Pratos inseridos com sucesso!");

            // Listando pratos
            System.out.println("\n--- Pratos ---");
            for (Prato p : pratoDao.listarTodos()) {
                System.out.println(p);
            }

            // Inserindo ingredientes
            ingredienteDao.inserir(new Ingrediente(0, "Macarrão",     "gramas"));
            ingredienteDao.inserir(new Ingrediente(0, "Carne Moída",  "gramas"));
            ingredienteDao.inserir(new Ingrediente(0, "Molho de Tomate", "ml"));
            ingredienteDao.inserir(new Ingrediente(0, "Frango",       "gramas"));
            ingredienteDao.inserir(new Ingrediente(0, "Azeite",       "ml"));
            ingredienteDao.inserir(new Ingrediente(0, "Alface",       "folhas"));
            ingredienteDao.inserir(new Ingrediente(0, "Croutons",     "gramas"));
            ingredienteDao.inserir(new Ingrediente(0, "Molho Caesar", "ml"));
            System.out.println("\nIngredientes inseridos com sucesso!");

            // Listando ingredientes
            System.out.println("\n--- Ingredientes ---");
            for (Ingrediente i : ingredienteDao.listarTodos()) {
                System.out.println(i);
            }

            // Inserindo receitas (prato 1 - Macarrão à Bolonhesa)
            receitaDao.inserir(new Receita(1, 1, 200.0)); // Macarrão
            receitaDao.inserir(new Receita(1, 2, 150.0)); // Carne Moída
            receitaDao.inserir(new Receita(1, 3, 100.0)); // Molho de Tomate

            // Inserindo receitas (prato 2 - Frango Grelhado)
            receitaDao.inserir(new Receita(2, 4, 300.0)); // Frango
            receitaDao.inserir(new Receita(2, 5,  30.0)); // Azeite

            // Inserindo receitas (prato 3 - Salada Caesar)
            receitaDao.inserir(new Receita(3, 6,   5.0)); // Alface
            receitaDao.inserir(new Receita(3, 7,  50.0)); // Croutons
            receitaDao.inserir(new Receita(3, 8,  40.0)); // Molho Caesar
            System.out.println("\nReceitas inseridas com sucesso!");

            // Listando ingredientes do prato 1
            System.out.println("\n--- Ingredientes do Prato 1 (Macarrão à Bolonhesa) ---");
            List<String> ingredientes = receitaDao.listarIngredientesPorPrato(1);
            for (String linha : ingredientes) {
                System.out.println(linha);
            }

            // Listando ingredientes do prato 2
            System.out.println("\n--- Ingredientes do Prato 2 (Frango Grelhado) ---");
            for (String linha : receitaDao.listarIngredientesPorPrato(2)) {
                System.out.println(linha);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}