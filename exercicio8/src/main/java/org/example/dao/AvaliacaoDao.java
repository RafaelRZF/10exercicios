package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Avaliacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Avaliacao.
 */
public class AvaliacaoDao {

    /**
     * Insere uma nova avaliação no banco de dados.
     */
    public void inserir(Avaliacao a) throws SQLException {
        String sql = "INSERT INTO Avaliacoes (usuario_id, filme_id, nota, "
                + "comentario, data_avaliacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getUsuarioId());
            ps.setInt(2, a.getFilmeId());
            ps.setInt(3, a.getNota());
            ps.setString(4, a.getComentario());
            ps.setObject(5, a.getDataAvaliacao());
            ps.executeUpdate();
        }
    }

    /**
     * Calcula a média de avaliação de um filme específico
     * e lista todos os comentários para esse filme.
     * @param filmeId ID do filme a consultar
     */
    public void exibirMediaEComentarios(int filmeId) throws SQLException {
        // Consulta da média
        String sqlMedia = "SELECT f.titulo, AVG(a.nota) AS media_nota " +
                "FROM Filmes f " +
                "INNER JOIN Avaliacoes a ON f.id = a.filme_id " +
                "WHERE f.id = ? " +
                "GROUP BY f.id, f.titulo";

        // Consulta dos comentários
        String sqlComentarios = "SELECT u.nome, a.nota, a.comentario, a.data_avaliacao " +
                "FROM Avaliacoes a " +
                "INNER JOIN Usuarios u ON a.usuario_id = u.id " +
                "WHERE a.filme_id = ? " +
                "ORDER BY a.data_avaliacao DESC";

        try (Connection con = Conexao.getConexao()) {
            // Exibe a média
            try (PreparedStatement ps = con.prepareStatement(sqlMedia)) {
                ps.setInt(1, filmeId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.println("Filme: " + rs.getString("titulo")
                            + " | Média: " + String.format("%.1f", rs.getDouble("media_nota")));
                }
            }
            // Exibe os comentários
            try (PreparedStatement ps = con.prepareStatement(sqlComentarios)) {
                ps.setInt(1, filmeId);
                ResultSet rs = ps.executeQuery();
                System.out.println("\nComentários:");
                while (rs.next()) {
                    System.out.println("  " + rs.getString("nome")
                            + " (nota " + rs.getInt("nota") + "): "
                            + rs.getString("comentario"));
                }
            }
        }
    }
}