package org.example.model;

import java.time.LocalDateTime;

/**
 * Representa uma consulta médica agendada.
 * Uma consulta está associada a um paciente e a um médico.
 */
public class Consulta {

    private int           id;
    private int           pacienteId;
    private int           medicoId;
    private LocalDateTime dataHora;
    private String        status;
    private String        observacoes;

    public Consulta() {}

    public Consulta(int id, int pacienteId, int medicoId,
                    LocalDateTime dataHora, String status, String observacoes) {
        this.id          = id;
        this.pacienteId  = pacienteId;
        this.medicoId    = medicoId;
        this.dataHora    = dataHora;
        this.status      = status;
        this.observacoes = observacoes;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public int getMedicoId() { return medicoId; }
    public void setMedicoId(int medicoId) { this.medicoId = medicoId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    @Override
    public String toString() {
        return "Consulta{id=" + id + ", pacienteId=" + pacienteId
                + ", medicoId=" + medicoId + ", dataHora=" + dataHora
                + ", status='" + status + "'}";
    }
}