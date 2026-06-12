package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 */
public class Conexao {

    private static final String URL     = "jdbc:mysql://localhost:3306/restaurante_db";
    private static final String USUARIO = "root";
    private static final String SENHA   = "";

    /**
     * Abre e retorna uma nova conexão com o banco de dados.
     * @return objeto Connection pronto para uso
     * @throws SQLException se a conexão falhar
     */
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}