package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Inscricao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Inscricao.
 */
public class InscricaoDao {

    /**
     * Insere uma nova inscrição no banco de dados.
     */
    public void inserir(Inscricao i) throws SQLException {
        String sql = "INSERT INTO Inscricoes (evento_id, participante_id, "
                + "data_inscricao, status_pagamento) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, i.getEventoId());
            ps.setInt(2, i.getParticipanteId());
            ps.setObject(3, i.getDataInscricao());
            ps.setString(4, i.getStatusPagamento());
            ps.executeUpdate();
        }
    }

    /**
     * Lista todos os participantes de um evento específico que já pagaram.
     * @param eventoId ID do evento a consultar
     */
    public List<String> listarParticipantesPagos(int eventoId) throws SQLException {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT p.nome, p.email " +
                "FROM Participantes p " +
                "INNER JOIN Inscricoes i ON p.id = i.participante_id " +
                "WHERE i.evento_id = ? AND i.status_pagamento = 'pago'";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resultado.add(rs.getString("nome") + " | " + rs.getString("email"));
            }
        }
        return resultado;
    }
}