package org.example.controller;

import org.example.dao.Conexao;
import org.example.models.Aluno;

import java.sql.*;

public class AlunosController {
    public void cadastrarAluno(Aluno aluno) {
        String sql = "INSERT INTO Alunos (nome, email, data_cadastro) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setTimestamp(3, Timestamp.valueOf(aluno.getDataCadastro()));
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    aluno.setId(rs.getInt(1));
                }
            }
            System.out.println("Aluno '" + aluno.getNome() + "' cadastrado com sucesso! ID: " + aluno.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
}