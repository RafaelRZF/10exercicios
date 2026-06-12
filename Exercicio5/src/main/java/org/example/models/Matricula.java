package org.example.models;

import java.time.LocalDateTime;

public class Matricula {
    private Curso curso;
    private Aluno javaAluno;
    private LocalDateTime dataMatricula;

    public Matricula() {}

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Aluno getAluno() {
        return javaAluno;
    }

    public void setAluno(Aluno aluno) {
        this.javaAluno = aluno;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public Matricula(Curso curso, Aluno aluno, LocalDateTime dataMatricula) {
        this.curso = curso;
        this.javaAluno = aluno;
        this.dataMatricula = dataMatricula;
    }
}