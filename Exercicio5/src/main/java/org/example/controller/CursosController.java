package org.example.controller;
import org.example.dao.Conexao;
import org.example.models.Curso;
import java.sql.*;

public class CursosController {
    public void cadastrarCurso(Curso curso) {
        String sql = "INSERT INTO Cursos (nome, carga_horaria) VALUES (?, ?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setId(rs.getInt(1));
                }
            }
            System.out.println("Curso '" + curso.getNome() + "' cadastrado com sucesso! ID: " + curso.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
        }
    }
}