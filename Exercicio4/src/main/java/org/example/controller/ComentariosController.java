package org.example.controller;

import org.example.dao.Conexao;
import org.example.models.Comentarios;

import java.sql.*;

public class ComentariosController {

    public void cadastrarComentario(Comentarios comentario) {
        String sql = "INSERT INTO Comentarios (post_id, autor, conteudo, data_comentario) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // comentario.getPostID() retorna o objeto Posts, e pega o id dele
            stmt.setInt(1, comentario.getPostID().getId());
            stmt.setString(2, comentario.getAutor());
            stmt.setString(3, comentario.getConteudo());
            stmt.setTimestamp(4, Timestamp.valueOf(comentario.getDataComentario()));

            stmt.executeUpdate();
            System.out.println("Comentário de " + comentario.getAutor() + " adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar comentário: " + e.getMessage());
        }
    }
}