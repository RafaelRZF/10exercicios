package org.example.view;

import org.example.controller.AlunosController;
import org.example.controller.CursosController;
import org.example.controller.MatriculasController;
import org.example.models.Aluno;
import org.example.models.Curso;
import org.example.models.Matricula;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instanciação dos controllers
        CursosController cursosController = new CursosController();
        AlunosController alunosController = new AlunosController();
        MatriculasController matriculasController = new MatriculasController();

        int opcao = 0;

        do {
            System.out.println("\n===== PLATAFORMA DE ENSINO (MATRÍCULAS) =====");
            System.out.println("1. Cadastrar Curso");
            System.out.println("2. Cadastrar Aluno");
            System.out.println("3. Matricular Aluno em um Curso");
            System.out.println("4. Listar Alunos por Curso (Consulta do Exercício)");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR CURSO ---");
                    System.out.print("Nome do Curso: ");
                    String nomeCurso = scanner.nextLine();
                    System.out.print("Carga Horária (em horas): ");
                    int carga = Integer.parseInt(scanner.nextLine());

                    Curso novoCurso = new Curso(nomeCurso, carga);
                    cursosController.cadastrarCurso(novoCurso);
                    break;

                case 2:
                    System.out.println("\n--- CADASTRAR ALUNO ---");
                    System.out.print("Nome do Aluno: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("Email do Aluno: ");
                    String email = scanner.nextLine();

                    Aluno novoAluno = new Aluno(nomeAluno, email, LocalDateTime.now());
                    alunosController.cadastrarAluno(novoAluno);
                    break;

                case 3:
                    System.out.println("\n--- MATRICULAR ALUNO EM CURSO ---");
                    System.out.print("Digite o ID do Aluno: ");
                    int idAlunoMatricula = Integer.parseInt(scanner.nextLine());
                    System.out.print("Digite o ID do Curso: ");
                    int idCursoMatricula = Integer.parseInt(scanner.nextLine());

                    // Criação de objetos reduzidos/fictícios contendo apenas o ID para servir de Chave Estrangeira (FK)
                    Curso cursoFicticio = new Curso();
                    cursoFicticio.setId(idCursoMatricula);

                    Aluno alunoFicticio = new Aluno();
                    alunoFicticio.setId(idAlunoMatricula);

                    // Criação da instância de Matrícula vinculando as partes
                    Matricula novaMatricula = new Matricula();
                    novaMatricula.setCurso(cursoFicticio);
                    novaMatricula.setAluno(alunoFicticio);
                    novaMatricula.setDataMatricula(LocalDateTime.now());

                    matriculasController.matricularAluno(novaMatricula);
                    break;

                case 4:
                    System.out.println("\n--- CONSULTAR ALUNOS POR CURSO ---");
                    System.out.print("Digite o ID do curso que deseja consultar: ");
                    int idCursoFiltro = Integer.parseInt(scanner.nextLine());

                    // Executa a consulta com INNER JOIN relacionando a tabela intermediária
                    matriculasController.listarAlunosPorCurso(idCursoFiltro);
                    break;

                case 5:
                    System.out.println("\nSaindo do sistema... Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}