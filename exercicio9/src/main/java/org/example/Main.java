package org.example;

import org.example.dao.ConsultaDao;
import org.example.dao.MedicoDao;
import org.example.dao.PacienteDao;
import org.example.model.Consulta;
import org.example.model.Medico;
import org.example.model.Paciente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe principal — demonstra o funcionamento do sistema de consultas médicas.
 */
public class Main {

    public static void main(String[] args) {

        PacienteDao  pacienteDao  = new PacienteDao();
        MedicoDao    medicoDao    = new MedicoDao();
        ConsultaDao  consultaDao  = new ConsultaDao();

        try {
            // Inserindo pacientes
            pacienteDao.inserir(new Paciente(0, "João Silva",
                    LocalDate.of(1990, 5, 15), "11999990001", "Rua A, 123"));
            pacienteDao.inserir(new Paciente(0, "Maria Santos",
                    LocalDate.of(1985, 8, 22), "11999990002", "Rua B, 456"));
            pacienteDao.inserir(new Paciente(0, "Pedro Oliveira",
                    LocalDate.of(2000, 3, 10), "11999990003", "Rua C, 789"));
            System.out.println("Pacientes inseridos com sucesso!");

            // Listando pacientes
            System.out.println("\n--- Pacientes ---");
            for (Paciente p : pacienteDao.listarTodos()) {
                System.out.println(p);
            }

            // Inserindo médicos
            medicoDao.inserir(new Medico(0, "Dr. Carlos Lima",    "Cardiologia",  "CRM12345"));
            medicoDao.inserir(new Medico(0, "Dra. Ana Ferreira",  "Pediatria",    "CRM67890"));
            medicoDao.inserir(new Medico(0, "Dr. Paulo Mendes",   "Ortopedia",    "CRM11223"));
            System.out.println("\nMédicos inseridos com sucesso!");

            // Listando médicos
            System.out.println("\n--- Médicos ---");
            for (Medico m : medicoDao.listarTodos()) {
                System.out.println(m);
            }

            // Inserindo consultas
            consultaDao.inserir(new Consulta(0, 1, 1,
                    LocalDateTime.of(2025, 9, 15, 9, 0),
                    "agendada", "Consulta de rotina"));
            consultaDao.inserir(new Consulta(0, 2, 1,
                    LocalDateTime.of(2025, 9, 15, 10, 30),
                    "agendada", "Checkup cardíaco"));
            consultaDao.inserir(new Consulta(0, 3, 2,
                    LocalDateTime.of(2025, 9, 15, 14, 0),
                    "agendada", "Consulta pediátrica"));
            consultaDao.inserir(new Consulta(0, 1, 3,
                    LocalDateTime.of(2025, 9, 16, 8, 0),
                    "agendada", "Dor no joelho"));
            System.out.println("\nConsultas inseridas com sucesso!");

            // Listando consultas do médico 1 na data 2025-09-15
            System.out.println("\n--- Consultas do Dr. Carlos Lima em 15/09/2025 ---");
            List<String> consultas = consultaDao.listarConsultasPorMedicoEData(1, "2025-09-15");
            for (String linha : consultas) {
                System.out.println(linha);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}