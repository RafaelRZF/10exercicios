package controller;
import dao.Conexao;
import models.ItemPedido;
import models.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PedidoController {

    /**
     * 1. CREATE: Salvar o Pedido e seus respectivos Itens no MySQL
     */
    public void salvarPedido(Pedido pedido) {
        String sqlPedido = "INSERT INTO Pedidos (cliente_id, data_pedido, status) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO ItensPedido (pedido_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";

        // Abre a conexão utilizando a classe dao
        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) {
                System.out.println("Não foi possível salvar o pedido porque a conexão falhou.");
                return;
            }

            // 1º Inserir o Pedido base
            try (PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
                stmtPedido.setInt(1, pedido.getCliente().getId());
                stmtPedido.setDate(2, java.sql.Date.valueOf(pedido.getDataPedido()));
                stmtPedido.setString(3, pedido.getStatus());
                stmtPedido.executeUpdate();

                // Recupera o ID gerado pelo AUTO_INCREMENT do MySQL para usar nos itens
                try (ResultSet rs = stmtPedido.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        pedido.setId(idGerado);
                    }
                }

                // 2º Inserir os itens do pedido na tabela intermediária
                try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {
                    for (ItemPedido item : pedido.getItens()) {
                        stmtItem.setInt(1, pedido.getId());
                        stmtItem.setInt(2, item.getProduto().getId());
                        stmtItem.setInt(3, item.getQuantidade());
                        stmtItem.setDouble(4, item.getPrecoUnitario());
                        stmtItem.addBatch(); // Adiciona ao lote de execução
                    }
                    stmtItem.executeBatch();
                }

                // Confirma todas as alterações no banco se nenhuma linha falhar
                System.out.println("Pedido nº " + pedido.getId() + " registrado com sucesso no banco!");

            } catch (SQLException e) {
                System.out.println("ERRO: "+e.getMessage());
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL ao salvar pedido: " + e.getMessage());
        }
    }

    /**
     * 2. READ: Consulta Mostrar todos os pedidos de um cliente específico
     */
    public void mostrarPedidosDoCliente(int idCliente) {
        String sql = "SELECT p.id AS id_pedido, p.data_pedido, p.status, " +
                "prod.nome AS nome_produto, ip.quantidade, ip.preco_unitario " +
                "FROM Pedidos p " +
                "INNER JOIN ItensPedido ip ON p.id = ip.pedido_id " +
                "INNER JOIN Produtos prod ON ip.produto_id = prod.id " +
                "WHERE p.cliente_id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);

                try (ResultSet rs = stmt.executeQuery()) {
                    System.out.println("\n=== Histórico de Pedidos do Cliente ID: " + idCliente + " ===");

                    int pedidoAtualId = -1;

                    while (rs.next()) {
                        int idPedido = rs.getInt("id_pedido");

                        // Controle visual para agrupar múltiplos produtos sob o mesmo cabeçalho de pedido
                        if (idPedido != pedidoAtualId) {
                            System.out.println("\nPedido ID: " + idPedido +
                                    " | Data: " + rs.getDate("data_pedido") +
                                    " | Status: " + rs.getString("status"));
                            pedidoAtualId = idPedido;
                            System.out.println("   Produtos inclusos:");
                        }

                        String produto = rs.getString("nome_produto");
                        int qtd = rs.getInt("quantidade");
                        double precoUn = rs.getDouble("preco_unitario");
                        double subtotal = qtd * precoUn;

                        System.out.println("     • " + produto + " (Qtd: " + qtd + ") - Un: R$" + precoUn + " | Subtotal: R$" + subtotal);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * 3. UPDATE: Atualiza o status de um pedido existente
     */
    public void atualizarStatusPedido(int idPedido, String novoStatus) {
        String sql = "UPDATE Pedidos SET status = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, novoStatus);
                stmt.setInt(2, idPedido);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Status do pedido nº " + idPedido + " atualizado para: " + novoStatus);
                } else {
                    System.out.println("Pedido nº " + idPedido + " não foi encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * 4. DELETE: Remove o pedido e seus itens do banco de dados
     */
    public void deletarPedido(int idPedido) {
        String sqlItens = "DELETE FROM ItensPedido WHERE pedido_id = ?";
        String sqlPedido = "DELETE FROM Pedidos WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            // Ativa transação para garantir que apague ambos ou nenhum
            conn.setAutoCommit(false);

            try {
                // 1º Passo: Deleta os itens associados ao pedido
                try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
                    stmtItens.setInt(1, idPedido);
                    stmtItens.executeUpdate();
                }

                // 2º Passo: Deleta o pedido base
                try (PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido)) {
                    stmtPedido.setInt(1, idPedido);
                    int linhasAfetadas = stmtPedido.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("✅ Pedido nº " + idPedido + " e seus itens foram deletados com sucesso!");
                    } else {
                        System.out.println("⚠️ Pedido nº " + idPedido + " não foi encontrado.");
                    }
                }

                conn.commit(); // Confirma as exclusões

            } catch (SQLException e) {
                conn.rollback(); // Desfaz se algo der errado
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar o pedido: " + e.getMessage());
        }
    }
}