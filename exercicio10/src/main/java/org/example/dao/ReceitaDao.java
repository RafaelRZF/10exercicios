package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Receita.
 */
public class ReceitaDao {

    /**
     * Insere uma nova receita (relação prato-ingrediente) no banco de dados.
     */
    public void inserir(Receita r) throws SQLException {
        String sql = "INSERT INTO Receitas (prato_id, ingrediente_id, quantidade_necessaria) "
                + "VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, r.getPratoId());
            ps.setInt(2, r.getIngredienteId());
            ps.setDouble(3, r.getQuantidadeNecessaria());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os ingredientes necessários para um prato específico,
     * incluindo a quantidade de cada um.
     * @param pratoId ID do prato a consultar
     */
    public List<String> listarIngredientesPorPrato(int pratoId) throws SQLException {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT i.nome, i.unidade_medida, r.quantidade_necessaria " +
                "FROM Receitas r " +
                "INNER JOIN Ingredientes i ON r.ingrediente_id = i.id " +
                "WHERE r.prato_id = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pratoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getString("nome")
                        + " | Quantidade: " + rs.getDouble("quantidade_necessaria")
                        + " " + rs.getString("unidade_medida"));
            }
        }
        return resultado;
    }
}