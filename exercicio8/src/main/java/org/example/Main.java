package org.example;

import org.example.dao.AvaliacaoDao;
import org.example.dao.FilmeDao;
import org.example.dao.UsuarioDao;
import org.example.model.Avaliacao;
import org.example.model.Filme;
import org.example.model.Usuario;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Classe principal — demonstra o funcionamento do sistema de avaliação de filmes.
 */
public class Main {

    public static void main(String[] args) {

        UsuarioDao   usuarioDao   = new UsuarioDao();
        FilmeDao     filmeDao     = new FilmeDao();
        AvaliacaoDao avaliacaoDao = new AvaliacaoDao();

        try {
            // Inserindo usuários
            usuarioDao.inserir(new Usuario(0, "Ana Silva",    "ana@email.com"));
            usuarioDao.inserir(new Usuario(0, "Carlos Souza", "carlos@email.com"));
            usuarioDao.inserir(new Usuario(0, "Maria Lima",   "maria@email.com"));
            System.out.println("Usuários inseridos com sucesso!");

            // Listando usuários
            System.out.println("\n--- Usuários ---");
            for (Usuario u : usuarioDao.listarTodos()) {
                System.out.println(u);
            }

            // Inserindo filmes
            filmeDao.inserir(new Filme(0, "O Poderoso Chefão", 1972, "Francis Ford Coppola", "Drama"));
            filmeDao.inserir(new Filme(0, "Interestelar",       2014, "Christopher Nolan",    "Ficção Científica"));
            filmeDao.inserir(new Filme(0, "Parasita",           2019, "Bong Joon-ho",         "Drama"));
            System.out.println("\nFilmes inseridos com sucesso!");

            // Listando filmes
            System.out.println("\n--- Filmes ---");
            for (Filme f : filmeDao.listarTodos()) {
                System.out.println(f);
            }

            // Inserindo avaliações
            avaliacaoDao.inserir(new Avaliacao(0, 1, 1, 10,
                    "Obra-prima do cinema!", LocalDate.now()));
            avaliacaoDao.inserir(new Avaliacao(0, 2, 1, 9,
                    "Incrível, clássico absoluto.", LocalDate.now()));
            avaliacaoDao.inserir(new Avaliacao(0, 3, 1, 8,
                    "Muito bom, mas longo.", LocalDate.now()));
            avaliacaoDao.inserir(new Avaliacao(0, 1, 2, 10,
                    "Visualmente deslumbrante!", LocalDate.now()));
            avaliacaoDao.inserir(new Avaliacao(0, 2, 2, 8,
                    "Roteiro complexo mas envolvente.", LocalDate.now()));
            System.out.println("\nAvaliações inseridas com sucesso!");

            // Exibindo média e comentários do filme 1
            System.out.println("\n--- Avaliações do Filme 1 ---");
            avaliacaoDao.exibirMediaEComentarios(1);

            // Exibindo média e comentários do filme 2
            System.out.println("\n--- Avaliações do Filme 2 ---");
            avaliacaoDao.exibirMediaEComentarios(2);

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}