package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Usuario.
 */
public class UsuarioDao {

    /**
     * Insere um novo usuário no banco de dados.
     */
    public void inserir(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuarios (nome, email) VALUES (?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os usuários cadastrados.
     */
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }
        }
        return lista;
    }
}