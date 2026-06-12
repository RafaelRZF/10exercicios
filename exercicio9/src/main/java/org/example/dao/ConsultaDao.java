package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Consulta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Consulta.
 */
public class ConsultaDao {

    /**
     * Insere uma nova consulta no banco de dados.
     */
    public void inserir(Consulta c) throws SQLException {
        String sql = "INSERT INTO Consultas (paciente_id, medico_id, data_hora, "
                + "status, observacoes) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getPacienteId());
            ps.setInt(2, c.getMedicoId());
            ps.setObject(3, c.getDataHora());
            ps.setString(4, c.getStatus());
            ps.setString(5, c.getObservacoes());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todas as consultas agendadas para um médico específico em uma data.
     * @param medicoId ID do médico
     * @param data     Data no formato 'YYYY-MM-DD'
     */
    public List<String> listarConsultasPorMedicoEData(int medicoId,
                                                      String data) throws SQLException {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT p.nome AS paciente, m.nome AS medico, " +
                "c.data_hora, c.status, c.observacoes " +
                "FROM Consultas c " +
                "INNER JOIN Pacientes p ON c.paciente_id = p.id " +
                "INNER JOIN Medicos   m ON c.medico_id   = m.id " +
                "WHERE c.medico_id = ? AND DATE(c.data_hora) = ?";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, medicoId);
            ps.setString(2, data);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add("Paciente: " + rs.getString("paciente")
                        + " | Médico: "    + rs.getString("medico")
                        + " | Data/Hora: " + rs.getTimestamp("data_hora")
                        + " | Status: "    + rs.getString("status"));
            }
        }
        return resultado;
    }
}