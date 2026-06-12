package org.example.controller;

import org.example.dao.Conexao;
import org.example.models.Matricula;

import java.sql.*;

public class MatriculasController {

    /**
     * Efetua a matrícula de um Aluno em um Curso (Insere na tabela intermediária N:M)
     */
    public void matricularAluno(Matricula matricula) {
        String sql = "INSERT INTO Matriculas (curso_id, aluno_id, data_matricula) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getCurso().getId());
            stmt.setInt(2, matricula.getAluno().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(matricula.getDataMatricula()));

            stmt.executeUpdate();
            System.out.println("Matrícula efetuada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao efetuar matrícula: " + e.getMessage());
        }
    }

    /**
     * Consulta customizada: Lista todos os alunos matriculados em um curso específico,
     * incluindo a data em que a matrícula foi realizada.
     */
    public void listarAlunosPorCurso(int idCurso) {
        String sql = "SELECT a.id, a.nome, a.email, m.data_matricula " +
                "FROM Alunos a " +
                "INNER JOIN Matriculas m ON a.id = m.aluno_id " +
                "WHERE m.curso_id = ? " +
                "ORDER BY a.nome ASC";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);

            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("--- Alunos Matriculados no Curso ID: " + idCurso + " ---");
                boolean encontrou = false;
                while (rs.next()) {
                    encontrou = true;
                    System.out.println("ID: " + rs.getInt("id") +
                            " | Nome: " + rs.getString("nome") +
                            " | Email: " + rs.getString("email") +
                            " | Matriculado em: " + rs.getTimestamp("data_matricula").toLocalDateTime());
                }
                if (!encontrou) {
                    System.out.println("Nenhum aluno matriculado neste curso até o momento.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos do curso: " + e.getMessage());
        }
    }
}