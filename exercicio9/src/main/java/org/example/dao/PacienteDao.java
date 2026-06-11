package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Paciente.
 */
public class PacienteDao {

    /**
     * Insere um novo paciente no banco de dados.
     */
    public void inserir(Paciente p) throws SQLException {
        String sql = "INSERT INTO Pacientes (nome, data_nascimento, telefone, endereco) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setObject(2, p.getDataNascimento());
            ps.setString(3, p.getTelefone());
            ps.setString(4, p.getEndereco());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os pacientes cadastrados.
     */
    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pacientes";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Paciente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("endereco")
                ));
            }
        }
        return lista;
    }
}