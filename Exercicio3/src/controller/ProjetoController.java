package controller;

import dao.Conexao;
import models.Projeto;
import java.sql.*;

public class ProjetoController {
    public void cadastrarProjeto(Projeto projeto) {
        String sql = "INSERT INTO Projetos (nome, descricao, data_inicio, data_fim) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setDate(3, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(4, projeto.getDataFim() != null ? Date.valueOf(projeto.getDataFim()) : null);
            stmt.executeUpdate();
            System.out.println("Projeto criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Projeto buscarProjetoPorId(int id) {
        String sql = "SELECT * FROM Projetos WHERE id = ?";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date dataFimSql = rs.getDate("data_fim");
                    Projeto p = new Projeto(
                            rs.getString("nome"),
                            rs.getString("descricao"),
                            rs.getDate("data_inicio").toLocalDate(),
                            dataFimSql != null ? dataFimSql.toLocalDate() : null
                    );
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return null;
    }
}