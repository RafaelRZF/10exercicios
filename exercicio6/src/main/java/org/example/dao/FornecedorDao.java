package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Fornecedor.
 */
public class FornecedorDao {

    /**
     * Insere um novo fornecedor no banco de dados.
     */
    public void inserir(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO Fornecedores (nome, contato, telefone) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setString(2, f.getContato());
            ps.setString(3, f.getTelefone());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os fornecedores cadastrados.
     */
    public List<Fornecedor> listarTodos() throws SQLException {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedores";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Fornecedor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("contato"),
                        rs.getString("telefone")
                ));
            }
        }
        return lista;
    }
}