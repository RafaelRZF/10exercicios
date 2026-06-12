package org.example.model;

import java.time.LocalDate;

/**
 * Representa a inscrição de um participante em um evento.
 * Tabela associativa entre Eventos e Participantes (N:N).
 * O campo statusPagamento indica se o participante já pagou ou não.
 */
public class Inscricao {

    private int       eventoId;
    private int       participanteId;
    private LocalDate dataInscricao;
    private String    statusPagamento; // "pago" ou "pendente"

    public Inscricao() {}

    public Inscricao(int eventoId, int participanteId,
                     LocalDate dataInscricao, String statusPagamento) {
        this.eventoId        = eventoId;
        this.participanteId  = participanteId;
        this.dataInscricao   = dataInscricao;
        this.statusPagamento = statusPagamento;
    }

    public int getEventoId() { return eventoId; }
    public void setEventoId(int eventoId) { this.eventoId = eventoId; }

    public int getParticipanteId() { return participanteId; }
    public void setParticipanteId(int participanteId) { this.participanteId = participanteId; }

    public LocalDate getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDate dataInscricao) { this.dataInscricao = dataInscricao; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    @Override
    public String toString() {
        return "Inscricao{eventoId=" + eventoId + ", participanteId=" + participanteId
                + ", status='" + statusPagamento + "'}";
    }
}