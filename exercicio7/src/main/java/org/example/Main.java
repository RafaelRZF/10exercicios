package org.example;

import org.example.dao.EventoDao;
import org.example.dao.InscricaoDao;
import org.example.dao.ParticipanteDao;
import org.example.model.Evento;
import org.example.model.Inscricao;
import org.example.model.Participante;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Classe principal — demonstra o funcionamento do sistema de eventos e participantes.
 */
public class Main {

    public static void main(String[] args) {

        EventoDao       eventoDao       = new EventoDao();
        ParticipanteDao participanteDao = new ParticipanteDao();
        InscricaoDao    inscricaoDao    = new InscricaoDao();

        try {
            eventoDao.inserir(new Evento(0, "Conferência de TI",
                    LocalDate.of(2025, 8, 15), "São Paulo", "Evento sobre tecnologia"));
            eventoDao.inserir(new Evento(0, "Workshop de Java",
                    LocalDate.of(2025, 9, 10), "Rio de Janeiro", "Workshop prático de Java"));
            eventoDao.inserir(new Evento(0, "Seminário de Banco de Dados",
                    LocalDate.of(2025, 10, 5), "Curitiba", "Seminário sobre SQL e NoSQL"));
            System.out.println("Eventos inseridos com sucesso!");

            System.out.println("\n--- Eventos ---");
            for (Evento e : eventoDao.listarTodos()) {
                System.out.println(e);
            }

            participanteDao.inserir(new Participante(0, "Ana Silva",
                    "ana@email.com", "11999990001"));
            participanteDao.inserir(new Participante(0, "Carlos Souza",
                    "carlos@email.com", "11999990002"));
            participanteDao.inserir(new Participante(0, "Maria Oliveira",
                    "maria@email.com", "11999990003"));
            participanteDao.inserir(new Participante(0, "Pedro Costa",
                    "pedro@email.com", "11999990004"));
            System.out.println("\nParticipantes inseridos com sucesso!");

            System.out.println("\n--- Participantes ---");
            for (Participante p : participanteDao.listarTodos()) {
                System.out.println(p);
            }

            inscricaoDao.inserir(new Inscricao(1, 1, LocalDate.now(), "pago"));
            inscricaoDao.inserir(new Inscricao(1, 2, LocalDate.now(), "pendente"));
            inscricaoDao.inserir(new Inscricao(1, 3, LocalDate.now(), "pago"));
            inscricaoDao.inserir(new Inscricao(2, 1, LocalDate.now(), "pago"));
            inscricaoDao.inserir(new Inscricao(2, 4, LocalDate.now(), "pendente"));
            inscricaoDao.inserir(new Inscricao(3, 2, LocalDate.now(), "pago"));
            System.out.println("\nInscrições inseridas com sucesso!");

            System.out.println("\n--- Participantes pagos do Evento 1 ---");
            List<String> pagos = inscricaoDao.listarParticipantesPagos(1);
            for (String linha : pagos) {
                System.out.println(linha);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}