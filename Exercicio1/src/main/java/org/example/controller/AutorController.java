package org.example.controller;
import org.example.dao.Conexao;
import org.example.models.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorController {
    public void cadastrar(Autor autor) {
        String sql = "INSERT INTO Autor (nome) VALUES (?)";
        try (Connection conn = Conexao.obterConexao()) {

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, autor.getNome());
                    stmt.executeUpdate();
                    System.out.println("Autor cadastrado com sucesso!");
                }
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao executar comando SQL: " + e.getMessage());
        }
    }

    public List<Autor> listar() {
        String sql = "SELECT * FROM Autor";
        List<Autor> listaAutores = new ArrayList<>();

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        // Cria o objeto Autor com os dados vindo do MySQL
                        Autor autor = new Autor();
                        autor.setId(rs.getInt("id"));
                        autor.setNome(rs.getString("nome"));

                        listaAutores.add(autor);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar autores: " + e.getMessage());
        }
        return listaAutores;
    }

    public void atualizar(Autor autor) {
        String sql = "UPDATE Autor SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, autor.getNome());
                    stmt.setInt(2, autor.getId());

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Autor atualizado com sucesso!");
                    } else {
                        System.out.println("⚠Nenhum autor encontrado com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar autor: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Autor WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Autor deletado com sucesso!");
                    } else {
                        System.out.println("⚠Nenhum autor encontrado com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar autor: " + e.getMessage());
        }
    }
}
