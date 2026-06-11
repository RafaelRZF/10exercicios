package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Produto.
 */
public class ProdutoDao {

    /**
     * Insere um novo produto no banco de dados.
     */
    public void inserir(Produto p) throws SQLException {
        String sql = "INSERT INTO Produtos (nome, descricao, preco_compra, preco_venda, fornecedor_id) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setDouble(3, p.getPrecoCompra());
            ps.setDouble(4, p.getPrecoVenda());
            ps.setInt(5, p.getFornecedorId());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os produtos cadastrados.
     */
    public List<Produto> listarTodos() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Produtos";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("preco_compra"),
                        rs.getDouble("preco_venda"),
                        rs.getInt("fornecedor_id")
                ));
            }
        }
        return lista;
    }
}