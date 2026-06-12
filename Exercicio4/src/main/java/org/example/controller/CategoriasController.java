package org.example.controller;

import org.example.dao.Conexao;
import org.example.models.Categorias;

import java.sql.*;

public class CategoriasController {

    public void cadastrarCategoria(Categorias categoria) {
        String sql = "INSERT INTO Categorias (nome) VALUES (?)";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1));
                }
            }
            System.out.println("Categoria '" + categoria.getNome() + "' cadastrada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void listarCategorias() {
        String sql = "SELECT * FROM Categorias";
        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}