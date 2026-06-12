package view;

import controller.ProjetoController;
import controller.TarefaController;
import controller.UsuarioController;
import models.Projeto;
import models.Tarefa;
import models.Usuario;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Classe principal que atua como a camada de View (Interface de Usuário) para o Exercício 3.
 * Oferece um menu interativo via console para o gerenciamento de equipes, projetos e suas
 * respectivas tarefas associadas, integrando os dados diretamente com o banco de dados MySQL.
 */
public class Main {
    public static void main(String[] args) {
        // Inicialização do Scanner para capturar as entradas de dados do usuário no terminal
        Scanner scanner = new Scanner(System.in);

        // Instanciação das classes controladoras (Controllers) para gerenciar as regras e persistência
        UsuarioController usuarioController = new UsuarioController();
        ProjetoController projetoController = new ProjetoController();
        TarefaController tarefaController = new TarefaController();

        int opcao;

        // Loop de execução principal do sistema de gerenciamento
        do {
            System.out.println("\n=== GESTÃO DE PROJETOS E TAREFAS ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Criar Novo Projeto");
            System.out.println("3 - Atribuir Tarefa a um Usuário");
            System.out.println("4 - Listar Tarefas de um Projeto (Questão do Exercício)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza crucial do buffer do teclado para evitar saltos em leituras consecutivas de texto

            // Estrutura de controle de fluxo de menus baseada na especificação moderna do Java
            switch (opcao) {

                // === CASO 1: CADASTRO DE USUÁRIOS (Membros da Equipe) ===
                case 1 -> {
                    System.out.print("Nome do Usuário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    // Cria a instância do modelo sem definir ID (o AUTO_INCREMENT do MySQL cuida disso)
                    usuarioController.cadastrarUsuario(new Usuario(nome, email));
                }

                // === CASO 2: CRIAÇÃO DE NOVOS PROJETOS ===
                case 2 -> {
                    System.out.print("Nome do Projeto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    // Captura e conversão de datas utilizando a API moderna java.time (LocalDate)
                    // Espera o formato padrão internacional ISO-8601 (AAAA-MM-DD)
                    System.out.print("Data de Início (AAAA-MM-DD): ");
                    LocalDate inicio = LocalDate.parse(scanner.nextLine());

                    // Tratamento lógico para data de encerramento opcional (pode ser nula se o projeto estiver em andamento)
                    System.out.print("Data de Fim (AAAA-MM-DD ou deixe em branco): ");
                    String fimStr = scanner.nextLine();
                    LocalDate fim = fimStr.isEmpty() ? null : LocalDate.parse(fimStr);

                    // Envia a entidade populada para o respectivo controlador realizar o INSERT no banco
                    projetoController.cadastrarProjeto(new Projeto(nome, descricao, inicio, fim));
                }

                // === CASO 3: ATRIBUIR UMA TAREFA A UM USUÁRIO (Relação de dependência) ===
                case 3 -> {
                    // Passo 1: Solicita o ID do projeto e busca sua existência física no banco de dados
                    System.out.print("ID do Projeto correspondente: ");
                    int idProj = scanner.nextInt();
                    Projeto projeto = projetoController.buscarProjetoPorId(idProj);

                    // Passo 2: Solicita o ID do colaborador e valida sua existência física no banco
                    System.out.print("ID do Usuário responsável: ");
                    int idUser = scanner.nextInt();
                    Usuario usuario = usuarioController.buscarUsuarioPorId(idUser);
                    scanner.nextLine(); // Limpeza do buffer após ler dados numéricos

                    // Regra de Negócio e Integridade Referencial:
                    // Garante que uma tarefa não seja órfã de projeto ou vinculada a um usuário inexistente
                    if (projeto == null || usuario == null) {
                        System.out.println("⚠️ Validação falhou: Projeto ou Usuário inexistente no banco de dados.");
                        break; // Aborta este bloco switch e retorna com segurança ao menu inicial
                    }

                    // Passo 3: Se as checagens das Chaves Estrangeiras passaram, recolhe os detalhes da tarefa
                    System.out.print("Descrição da Tarefa: ");
                    String desc = scanner.nextLine();
                    System.out.print("Status Inicial (ex: Pendente, Em Andamento): ");
                    String status = scanner.nextLine();
                    System.out.print("Data de Vencimento (AAAA-MM-DD): ");
                    LocalDate vencimento = LocalDate.parse(scanner.nextLine());

                    // Instancia a classe associativa Tarefa conectando os objetos e chama o salvamento em lote
                    tarefaController.cadastrarTarefa(new Tarefa(projeto, usuario, desc, status, vencimento));
                }

                // === CASO 4: RELATÓRIO DO ENUNCIADO (Inner Join de Tarefas por Projeto) ===
                case 4 -> {
                    System.out.print("Digite o ID do Projeto para listar as tarefas: ");
                    int idProj = scanner.nextInt();

                    // Aciona o controlador responsável por rodar a query SQL unificada com junção de tabelas
                    tarefaController.listarTarefasPorProjeto(idProj);
                }

                // === CASO 0: FINALIZAÇÃO DO PROGRAMA ===
                case 0 -> System.out.println("Finalizando a aplicação...");

                // Clausula de salvaguarda para entradas numéricas inválidas fora do intervalo do menu
                default -> System.out.println("⚠️ Opção inválida!");
            }
        } while (opcao != 0); // Mantém o ciclo do console ativo até o comando de saída explicito
    }
}