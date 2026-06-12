package controller;

import dao.Conexao;
import models.Usuario;
import java.sql.*;

public class UsuarioController {
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nome, email) VALUES (?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(rs.getString("nome"), rs.getString("email"));
                    u.setId(rs.getInt("id"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}