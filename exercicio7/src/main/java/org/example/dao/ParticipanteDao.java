package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Participante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Participante.
 */
public class ParticipanteDao {

    /**
     * Insere um novo participante no banco de dados.
     */
    public void inserir(Participante p) throws SQLException {
        String sql = "INSERT INTO Participantes (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getEmail());
            ps.setString(3, p.getTelefone());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os participantes cadastrados.
     */
    public List<Participante> listarTodos() throws SQLException {
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT * FROM Participantes";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Participante(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                ));
            }
        }
        return lista;
    }
}