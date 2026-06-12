package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Ingrediente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Ingrediente.
 */
public class IngredienteDao {

    /**
     * Insere um novo ingrediente no banco de dados.
     */
    public void inserir(Ingrediente i) throws SQLException {
        String sql = "INSERT INTO Ingredientes (nome, unidade_medida) VALUES (?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getNome());
            ps.setString(2, i.getUnidadeMedida());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os ingredientes cadastrados.
     */
    public List<Ingrediente> listarTodos() throws SQLException {
        List<Ingrediente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ingredientes";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Ingrediente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("unidade_medida")
                ));
            }
        }
        return lista;
    }
}