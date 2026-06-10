package org.example.controller;
import org.example.dao.Conexao;
import org.example.models.Autor;
import org.example.models.Editora;
import org.example.models.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroController {
    public void cadastrar(Livro livro) {
        // O banco de dados recebe as chaves estrangeiras (IDs) do autor e da editora
        String sql = "INSERT INTO Livro (titulo, ano_publicacao, autor_id, editora_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    // Preenche os dados primitivos
                    stmt.setString(1, livro.getTitulo());
                    stmt.setInt(2, livro.getAnoPublicacao());

                    // Extrai o ID do objeto Autor associado
                    stmt.setInt(3, livro.getAutor_id().getId());

                    // Extrai o ID do objeto Editora associado
                    stmt.setInt(4, livro.getEditora_id().getId());

                    stmt.executeUpdate();
                    System.out.println("Livro cadastrado com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();

        // SQL com JOIN para trazer os dados das tabelas relacionadas
        String sql = "SELECT l.id AS livro_id, l.titulo, l.ano_publicacao, " +
                "a.id AS autor_id, a.nome AS autor_nome, a.nacionalidade, " +
                "e.id AS editora_id, e.nome AS editora_nome, e.cidade " +
                "FROM Livro l " +
                "INNER JOIN Autor a ON l.autor_id = a.id " +
                "INNER JOIN Editora e ON l.editora_id = e.id";

        try (Connection conn = Conexao.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // 1. Reconstrói o objeto Autor com os dados do banco
                Autor autor = new Autor(rs.getString("autor_nome"), rs.getString("nacionalidade"));
                autor.setId(rs.getInt("autor_id"));

                // 2. Reconstrói o objeto Editora com os dados do banco
                Editora editora = new Editora(rs.getString("editora_nome"), rs.getString("cidade"));
                editora.setId(rs.getInt("editora_id"));

                // 3. Cria o objeto Livro utilizando o construtor padrão da classe
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getInt("ano_publicacao"),
                        autor,
                        editora
                );
                livro.setId(rs.getInt("livro_id"));

                // 4. Adiciona o livro preenchido à lista
                livros.add(livro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return livros;
    }

    public void atualizar(Livro livro) {
        // SQL atualiza os campos da tabela filtrando pelo ID do livro
        String sql = "UPDATE Livro SET titulo = ?, ano_publicacao = ?, autor_id = ?, editora_id = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    // Mapeia os novos valores para os '?' correspondentes
                    stmt.setString(1, livro.getTitulo());
                    stmt.setInt(2, livro.getAnoPublicacao());
                    stmt.setInt(3, livro.getAutor_id().getId());
                    stmt.setInt(4, livro.getEditora_id().getId());

                    // Define o ID do livro no WHERE
                    stmt.setInt(5, livro.getId());

                    // Executa a atualização e verifica se alguma linha foi alterada
                    int linhasAfetadas = stmt.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Livro atualizado com sucesso!");
                    } else {
                        System.out.println("Nenhum livro foi encontrado com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Livro WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                    // Define o ID no WHERE do comando SQL
                    stmt.setInt(1, id);

                    // Executa a exclusão e verifica se alguma linha foi afetada
                    int linhasAfetadas = stmt.executeUpdate();

                    if (linhasAfetadas > 0) {
                        System.out.println("Livro deletado com sucesso!");
                    } else {
                        System.out.println("Nenhum livro foi encontrado com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro: " + e.getMessage());
        }
    }

}
