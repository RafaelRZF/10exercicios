package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Medico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Medico.
 */
public class MedicoDao {

    /**
     * Insere um novo médico no banco de dados.
     */
    public void inserir(Medico m) throws SQLException {
        String sql = "INSERT INTO Medicos (nome, especialidade, crm) VALUES (?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNome());
            ps.setString(2, m.getEspecialidade());
            ps.setString(3, m.getCrm());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os médicos cadastrados.
     */
    public List<Medico> listarTodos() throws SQLException {
        List<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medicos";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Medico(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade"),
                        rs.getString("crm")
                ));
            }
        }
        return lista;
    }
}