package org.example.view;

import org.example.controller.AutorController;
import org.example.controller.EditoraController;
import org.example.controller.LivroController;
import org.example.models.Autor;
import org.example.models.Editora;
import org.example.models.Livro;

import java.util.List;
import java.util.Scanner;

class Main{
    private static Scanner scanner = new Scanner(System.in);
    private static LivroController livroController = new LivroController();
    private static AutorController autorController = new AutorController();
    private static EditoraController editoraController = new EditoraController();

    static void main(String[] args) {

        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
            System.out.println("1 - Gerenciar Autores");
            System.out.println("2 - Gerenciar Editoras");
            System.out.println("3 - Gerenciar Livros");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer do teclado

            switch (opcao) {
                case 1 -> menuAutores();
                case 2 -> menuEditoras();
                case 3 -> menuLivros();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // ================= MENU AUTORES =================
    private static void menuAutores() {
        System.out.println("\n--- MENU AUTORES ---");
        System.out.println("1 - Cadastrar Autor");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Nome do Autor: ");
            String nome = scanner.nextLine();
            System.out.print("Nacionalidade: ");
            String nacionalidade = scanner.nextLine();

            Autor autor = new Autor(nome, nacionalidade);
            autorController.cadastrar(autor);
        }
    }

    // ================= MENU EDITORAS =================
    private static void menuEditoras() {
        System.out.println("\n--- MENU EDITORAS ---");
        System.out.println("1 - Cadastrar Editora");
        System.out.println("0 - Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Nome da Editora: ");
            String nome = scanner.nextLine();
            System.out.print("Cidade: ");
            String cidade = scanner.nextLine();

            Editora editora = new Editora(nome, cidade);
            editoraController.cadastrar(editora);
        }
    }

    // ================= MENU LIVROS (CRUD COMPLETO) =================
    private static void menuLivros() {
        int opcao;
        do {
            System.out.println("\n--- MENU LIVROS ---");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Atualizar Livro");
            System.out.println("4 - Deletar Livro");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> listarLivros();
                case 3 -> atualizarLivro();
                case 4 -> deletarLivro();
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano de Publicação: ");
        int ano = scanner.nextInt();

        System.out.println("AUTORES CADASTRADOS: ");
        if(!autorController.listar().isEmpty()){
            for(Autor autor : autorController.listar()){
                System.out.println("ID: "+autor.getId());
                System.out.println("Nome: "+autor.getNome());
            }
        }else{
            System.out.println("Não tem nenhun autor cadastrado.");
        }
        System.out.print("ID do Autor existente: ");
        int idAutor = scanner.nextInt();
        System.out.println("EDITORAS CADASTRADAS: ");
        if(!editoraController.listar().isEmpty()){
            for(Editora editora : editoraController.listar()){
                System.out.println("ID: "+editora.getId());
                System.out.println("Nome: "+editora.getNome());
            }
        }else{
            System.out.println("Não tem nenhuna editora cadastrada.");
        }
        System.out.print("ID da Editora existente: ");
        int idEditora = scanner.nextInt();
        scanner.nextLine();

        // Cria os objetos de referência mockados apenas com o ID para o relacionamento
        Autor autor = new Autor("", "");
        autor.setId(idAutor);

        Editora editora = new Editora("", "");
        editora.setId(idEditora);

        Livro livro = new Livro(titulo, ano, autor, editora);
        livroController.cadastrar(livro);
    }

    private static void listarLivros() {
        List<Livro> livros = livroController.listarTodos();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        System.out.println("\n--- LISTA DE LIVROS ---");
        for (Livro l : livros) {
            System.out.printf("ID: %d | Título: %s (%d) | Autor: %s | Editora: %s (%s)\n",
                    l.getId(), l.getTitulo(), l.getAnoPublicacao(),
                    l.getAutor_id().getNome(), l.getEditora_id().getNome(), l.getEditora_id().getCidade());
        }
    }

    private static void atualizarLivro() {
        System.out.print("Digite o ID do livro que deseja atualizar: ");
        int idLivro = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Novo Ano de Publicação: ");
        int ano = scanner.nextInt();
        System.out.print("Novo ID do Autor: ");
        int idAutor = scanner.nextInt();
        System.out.print("Novo ID da Editora: ");
        int idEditora = scanner.nextInt();
        scanner.nextLine();

        Autor autor = new Autor();
        autor.setId(idAutor);

        Editora editora = new Editora();
        editora.setId(idEditora);

        Livro livro = new Livro(titulo, ano, autor, editora);
        livro.setId(idLivro);

        livroController.atualizar(livro);
    }

    private static void deletarLivro() {
        System.out.print("Digite o ID do livro que deseja deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        livroController.deletar(id);
    }
}