package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Filme.
 */
public class FilmeDao {

    /**
     * Insere um novo filme no banco de dados.
     */
    public void inserir(Filme f) throws SQLException {
        String sql = "INSERT INTO Filmes (titulo, ano_lancamento, diretor, genero) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, f.getTitulo());
            ps.setInt(2, f.getAnoLancamento());
            ps.setString(3, f.getDiretor());
            ps.setString(4, f.getGenero());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os filmes cadastrados.
     */
    public List<Filme> listarTodos() throws SQLException {
        List<Filme> lista = new ArrayList<>();
        String sql = "SELECT * FROM Filmes";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("ano_lancamento"),
                        rs.getString("diretor"),
                        rs.getString("genero")
                ));
            }
        }
        return lista;
    }
}