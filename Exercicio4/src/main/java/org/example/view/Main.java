package org.example.view;

import org.example.controller.CategoriasController;
import org.example.controller.ComentariosController;
import org.example.controller.PostsController;
import org.example.models.Categorias;
import org.example.models.Comentarios;
import org.example.models.Posts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe principal que atua como a camada de View (Interface com o Usuário) do sistema de Blog.
 * Gerencia a interação via terminal utilizando um menu interativo.
 */
public class Main {
    public static void main(String[] args) {
        // Scanner para capturar as entradas digitadas pelo usuário no console
        Scanner scanner = new Scanner(System.in);

        // Instanciação dos controllers que fazem a ponte entre esta View e a camada de dados (MySQL)
        CategoriasController categoriasController = new CategoriasController();
        PostsController postsController = new PostsController();
        ComentariosController comentariosController = new ComentariosController();

        // Variável que armazena a opção escolhida pelo usuário no menu
        int opcao = 0;

        // Loop principal do menu que continuará rodando até que a opção 5 (Sair) seja escolhida
        do {
            System.out.println("\n=====SISTEMA DE BLOG===== ");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Cadastrar Post (com Categorias)");
            System.out.println("3. Adicionar Comentário em um Post");
            System.out.println("4. Listar Posts por Categoria (Consulta do Exercício)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            // Bloco try-catch para capturar erros caso o usuário digite letras onde se esperam números
            try {
                // Lê a linha digitada e a converte para inteiro, evitando problemas de quebra de linha do Scanner
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                continue; // Interrompe a iteração atual do loop e volta para o início do 'do'
            }

            // Estrutura de decisão para direcionar o fluxo baseado na opção escolhida
            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR CATEGORIA ---");
                    System.out.print("Nome da categoria: ");
                    String nomeCat = scanner.nextLine();

                    // Instancia o modelo Categoria e envia para o controller salvar no banco
                    Categorias novaCategoria = new Categorias(nomeCat);
                    categoriasController.cadastrarCategoria(novaCategoria);
                    break;

                case 2:
                    System.out.println("\n--- CADASTRAR POST ---");
                    System.out.print("Título do Post: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Conteúdo do Post: ");
                    String conteudo = scanner.nextLine();

                    // Instancia o modelo Posts e preenche seus atributos locais (o ID será gerado pelo MySQL)
                    Posts novoPost = new Posts();
                    novoPost.setTitulo(titulo);
                    novoPost.setConteudo(conteudo);
                    novoPost.setDataPublicacao(LocalDateTime.now()); // Define o horário exato da publicação

                    // Lista dinâmica para armazenar as categorias que o usuário deseja vincular ao post
                    List<Categorias> categoriesSelecionadas = new ArrayList<>();
                    System.out.print("Quantas categorias deseja associar a este post? ");
                    int qtdCategorias = Integer.parseInt(scanner.nextLine());

                    // Loop para capturar o ID de cada categoria selecionada
                    for (int i = 0; i < qtdCategorias; i++) {
                        System.out.print("Digite o ID da " + (i + 1) + "ª categoria cadastrada no banco: ");
                        int idCat = Integer.parseInt(scanner.nextLine());

                        // Cria um objeto temporário contendo apenas o ID.
                        // O JDBC utilizará esse ID para preencher a tabela intermediária PostCategorias
                        Categorias c = new Categorias();
                        c.setId(idCat);
                        categoriesSelecionadas.add(c);
                    }

                    // Envia o post e a lista de categorias vinculadas para o processamento transacional no banco
                    postsController.cadastrarPost(novoPost, categoriesSelecionadas);
                    break;

                case 3:
                    System.out.println("\n--- ADICIONAR COMENTÁRIO ---");
                    System.out.print("Digite o ID do Post que receberá o comentário: ");
                    int idPostTarget = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nome do Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Comentário: ");
                    String textoComentario = scanner.nextLine();

                    // Cria um objeto fictício/temporário de Post apenas com o ID informado.
                    // Isso simula a Chave Estrangeira (FK) necessária para o relacionamento 1:N no banco de dados.
                    Posts postFicticio = new Posts();
                    postFicticio.setId(idPostTarget);

                    // Instancia e popula o modelo de Comentários
                    Comentarios novoComentario = new Comentarios();
                    novoComentario.setAutor(autor);
                    novoComentario.setConteudo(textoComentario);
                    novoComentario.setDataComentario(LocalDateTime.now());
                    novoComentario.setPostID(postFicticio); // injeta o post alvo (FK) dentro do comentário

                    // Envia o comentário para ser persistido no MySQL
                    comentariosController.cadastrarComentario(novoComentario);
                    break;

                case 4:
                    System.out.println("\n--- CONSULTAR POSTS POR CATEGORIA ---");
                    System.out.print("Digite o ID da categoria que deseja filtrar: ");
                    int idCatFiltro = Integer.parseInt(scanner.nextLine());

                    // Invoca o método que executa a Query SQL complexa (utilizando JOIN, LEFT JOIN, COUNT e GROUP BY)
                    postsController.listarPostsPorCategoria(idCatFiltro);
                    break;

                case 5:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5); // O loop termina quando a opção for igual a 5
    }
}