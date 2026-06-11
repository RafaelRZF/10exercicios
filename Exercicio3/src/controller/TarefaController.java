package controller;

import dao.Conexao;
import models.Tarefa;
import java.sql.*;

public class TarefaController {
    public void cadastrarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO Tarefas (projeto_id, usuario_id, descricao, status, data_vencimento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tarefa.getProjeto().getId());
            stmt.setInt(2, tarefa.getUsuario().getId());
            stmt.setString(3, tarefa.getDescricao());
            stmt.setString(4, tarefa.getStatus());
            stmt.setDate(5, Date.valueOf(tarefa.getDataVencimento()));
            stmt.executeUpdate();
            System.out.println("Tarefa atribuída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarTarefasPorProjeto(int idProjeto) {
        String sql = "SELECT p.nome AS nome_projeto, t.descricao, u.nome AS nome_usuario, t.status, t.data_vencimento " +
                "FROM Tarefas t " +
                "INNER JOIN Projetos p ON t.projeto_id = p.id " +
                "INNER JOIN Usuarios u ON t.usuario_id = u.id " +
                "WHERE p.id = ?";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("\n=== Relatório de Tarefas do Projeto ===");
                boolean encontrou = false;

                while (rs.next()) {
                    if (!encontrou) {
                        System.out.println("Projeto: " + rs.getString("nome_projeto"));
                        System.out.println("----------------------------------------");
                        encontrou = true;
                    }
                    System.out.println("• Tarefa: " + rs.getString("descricao"));
                    System.out.println("  Responsável: " + rs.getString("nome_usuario"));
                    System.out.println("  Status: " + rs.getString("status"));
                    System.out.println("  Vencimento: " + rs.getDate("data_vencimento"));
                    System.out.println();
                }
                if (!encontrou) {
                    System.out.println("Nenhuma tarefa localizada para este projeto.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}