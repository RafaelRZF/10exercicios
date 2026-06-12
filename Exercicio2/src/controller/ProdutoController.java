package controller;
import dao.Conexao;
import models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoController {

    /**
     * C - CREATE: Adiciona um novo produto ao catálogo do MySQL
     */
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO Produtos (nome, preco, estoque) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPreco());
                stmt.setInt(3, produto.getQTDEstoque());
                stmt.executeUpdate();

                System.out.println("Produto \"" + produto.getNome() + "\" adicionado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * R - READ: Busca um produto específico pelo ID
     */
    public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM Produtos WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Produto produto = new Produto(
                                rs.getString("nome"),
                                rs.getDouble("preco"),
                                rs.getInt("estoque")
                        );

                        // Seta o ID recuperado do banco de dados
                        produto.setId(rs.getInt("id"));

                        return produto;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    /**
     * U - UPDATE: Atualiza os dados (preço, nome ou estoque) de um produto existente
     */
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE Produtos SET nome = ?, preco = ?, estoque = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, produto.getNome());
                stmt.setDouble(2, produto.getPreco());
                stmt.setInt(3, produto.getQTDEstoque());
                stmt.setInt(4, produto.getId());

                int linhas = stmt.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Produto ID " + produto.getId() + " atualizado no banco.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * D - DELETE: Remove o produto do catálogo
     */
    public void deletarProduto(int id) {
        String sql = "DELETE FROM Produtos WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                int linhas = stmt.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Produto ID " + id + " excluído com sucesso.");
                } else {
                    System.out.println("⚠️ Nenhum produto encontrado com o ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar produto (Verifique se ele não faz parte de nenhum pedido ativo): " + e.getMessage());
        }
    }
}