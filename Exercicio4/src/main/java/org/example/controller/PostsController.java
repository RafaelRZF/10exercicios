package org.example.controller;
import org.example.dao.Conexao;
import org.example.models.Categorias;
import org.example.models.Posts;

import java.sql.*;
import java.util.List;

public class PostsController {

    public void cadastrarPost(Posts post, List<Categorias> categorias) {
        String sqlPost = "INSERT INTO Posts (titulo, conteudo, data_publicacao) VALUES (?, ?, ?)";
        String sqlVinculo = "INSERT INTO PostCategorias (post_id, categoria_id) VALUES (?, ?)";

        try (Connection conn = Conexao.obterConexao()) {

            conn.setAutoCommit(false);

            try (PreparedStatement stmtPost = conn.prepareStatement(sqlPost, Statement.RETURN_GENERATED_KEYS)) {
                stmtPost.setString(1, post.getTitulo());
                stmtPost.setString(2, post.getConteudo());
                stmtPost.setTimestamp(3, Timestamp.valueOf(post.getDataPublicacao()));
                stmtPost.executeUpdate();

                // Recupera o ID que o MySQL gerou automaticamente
                try (ResultSet rs = stmtPost.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPostGerado = rs.getInt(1);
                        post.setId(idPostGerado); // Atualiza o objeto Java
                    }
                }
            }

            // Insere os vínculos na tabela intermediária Muitos-para-Muitos
            try (PreparedStatement stmtVinculo = conn.prepareStatement(sqlVinculo)) {
                for (Categorias cat : categorias) {
                    stmtVinculo.setInt(1, post.getId());
                    stmtVinculo.setInt(2, cat.getId());
                    stmtVinculo.addBatch(); // Adiciona ao lote
                }
                stmtVinculo.executeBatch(); // Executa tudo de uma vez
            }

            // Se tudo deu certo, confirma no banco
            System.out.println("Post e associações criados com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar post: " + e.getMessage());
        }
    }

    /**
     * Atende ao comando específico: Listar posts de uma categoria com a quantidade de comentários
     */
    public void listarPostsPorCategoria(int idCategoria) {
        String sql = "SELECT p.id, p.titulo, p.data_publicacao, COUNT(c.id) AS total_comentarios " +
                "FROM Posts p " +
                "INNER JOIN PostCategorias pc ON p.id = pc.post_id " +
                "LEFT JOIN Comentarios c ON p.id = c.post_id " +
                "WHERE pc.categoria_id = ? " +
                "GROUP BY p.id, p.titulo, p.data_publicacao";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("--- Posts da Categoria ID: " + idCategoria + " ---");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            " | Título: " + rs.getString("titulo") +
                            " | Publicado em: " + rs.getTimestamp("data_publicacao").toLocalDateTime() +
                            " | Comentários: " + rs.getInt("total_comentarios"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar posts da categoria: " + e.getMessage());
        }
    }
}