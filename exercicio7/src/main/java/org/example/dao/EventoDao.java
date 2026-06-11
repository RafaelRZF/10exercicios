package org.example.dao;

import org.example.db.Conexao;
import org.example.model.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações de banco de dados da entidade Evento.
 */
public class EventoDao {

    /**
     * Insere um novo evento no banco de dados.
     */
    public void inserir(Evento e) throws SQLException {
        String sql = "INSERT INTO Eventos (nome, data_evento, local, descricao) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNome());
            ps.setObject(2, e.getDataEvento());
            ps.setString(3, e.getLocal());
            ps.setString(4, e.getDescricao());
            ps.executeUpdate();
        }
    }

    /**
     * Retorna todos os eventos cadastrados.
     */
    public List<Evento> listarTodos() throws SQLException {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Eventos";
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_evento").toLocalDate(),
                        rs.getString("local"),
                        rs.getString("descricao")
                ));
            }
        }
        return lista;
    }
}