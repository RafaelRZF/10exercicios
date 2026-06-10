package org.example.controller;

public class EditoraController {
    public void cadastrar(Editora editora) {
        String sql = "INSERT INTO Editora (nome, cidade) VALUES (?, ?)";
        try (Connection conn = Conexao.obterConexao()) {

            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, editora.getNome());
                    stmt.setString(2, editora.getCidade());
                    stmt.executeUpdate();
                    System.out.println("Editora cadastrada com sucesso!");
                }
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Erro ao executar comando SQL: " + e.getMessage());
        }
    }

    public List<Autor> listar() {
        String sql = "SELECT * FROM Editora";
        List<Editora> listaEditoras = new ArrayList<>();

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        // Cria o objeto Editora com os dados vindo do MySQL
                        Editora editora = new Editora();
                        editora.setId(rs.getInt("id"));
                        editora.setNome(rs.getString("nome"));
                        editora.setNacionalidade(rs.getString("nacionalidade"));

                        listaEditoras.add(editora);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar editoras: " + e.getMessage());
        }
        return listaEditoras;
    }

    public void atualizar(Editora editora) {
        String sql = "UPDATE Editora SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, editora.getNome());
                    stmt.setInt(2, editora.getId());
                    stmt.setString(3, editora.getCidade());

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Editora atualizada com sucesso!");
                    } else {
                        System.out.println("⚠Nenhuma editora encontrado com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar editora: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Editora WHERE id = ?";

        try (Connection conn = Conexao.obterConexao()) {
            if (conn != null) {
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);

                    int linhasAfetadas = stmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Editora deletada com sucesso!");
                    } else {
                        System.out.println("⚠Nenhuma editora encontrada com o ID informado.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar editora: " + e.getMessage());
        }
    }
}
