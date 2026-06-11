package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Prato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Prato.
 */
public class PratoDao {

    /**
     * Insere um novo prato no banco de dados.
     */
    public void inserir(Prato p) throws SQLException {
        String sql = "INSERT INTO Pratos (nome, descricao, preco) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setDouble(3, p.getPreco());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os pratos cadastrados.
     */
    public List<Prato> listarTodos() throws SQLException {
        List<Prato> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pratos";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Prato(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco")
                ));
            }
        }
        return lista;
    }
}