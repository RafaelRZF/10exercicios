package controller;
import dao.Conexao;
import models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteController {

    /**
     * C - CREATE: Insere um novo cliente no banco
     */
    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nome, email, endereco) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getEndereco());
                stmt.executeUpdate();

                System.out.println("Cliente " + cliente.getNome() + " cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * R - READ: Busca um cliente pelo ID
     */
    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM Clientes WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return null;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Cliente cliente = new Cliente(
                                rs.getString("nome"),
                                rs.getString("email"),
                                rs.getString("endereco")
                        );

                        // 2. Insere o ID via Setter
                        cliente.setId(rs.getInt("id"));

                        return cliente;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }

    /**
     * U - UPDATE: Atualiza os dados de um cliente existente
     */
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE Clientes SET nome = ?, email = ?, endereco = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getEmail());
                stmt.setString(3, cliente.getEndereco());
                stmt.setInt(4, cliente.getId());

                int linhas = stmt.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Dados do cliente ID " + cliente.getId() + " atualizados!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * D - DELETE: Remove um cliente do banco pelo ID
     */
    public void deletarCliente(int id) {
        String sql = "DELETE FROM Clientes WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn == null) return;

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);

                int linhas = stmt.executeUpdate();
                if (linhas > 0) {
                    System.out.println("Cliente ID " + id + " removido com sucesso!");
                } else {
                    System.out.println("⚠️ Nenhum cliente encontrado com o ID " + id);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente (Verifique se ele não possui pedidos atrelados): " + e.getMessage());
        }
    }
}