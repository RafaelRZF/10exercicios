package org.example.dao;

import org.example.db.Conexao;
import org.example.model.TransacaoEstoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade TransacaoEstoque.
 */
public class TransacaoEstoqueDao {

    /**
     * Insere uma nova transação de estoque (entrada ou saída).
     */
    public void inserir(TransacaoEstoque t) throws SQLException {
        String sql = "INSERT INTO TransacoesEstoque (produto_id, tipo_transacao, quantidade, data_transacao) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, t.getProdutoId());
            ps.setString(2, t.getTipoTransacao());
            ps.setInt(3, t.getQuantidade());
            ps.setObject(4, t.getDataTransacao());
            ps.executeUpdate();
        }
    }

    /**
     * Calcula o estoque atual de cada produto somando entradas e subtraindo saídas.
     * Retorna uma lista de linhas no formato: "NomeProduto | Estoque: X"
     */
    public List<String> calcularEstoqueAtual() throws SQLException {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT p.nome, " +
                "SUM(CASE WHEN t.tipo_transacao = 'entrada' THEN t.quantidade ELSE 0 END) - " +
                "SUM(CASE WHEN t.tipo_transacao = 'saida'   THEN t.quantidade ELSE 0 END) AS estoque_atual " +
                "FROM Produtos p " +
                "LEFT JOIN TransacoesEstoque t ON p.id = t.produto_id " +
                "GROUP BY p.id, p.nome";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                resultado.add(rs.getString("nome") + " | Estoque: " + rs.getInt("estoque_atual"));
            }
        }
        return resultado;
    }
}