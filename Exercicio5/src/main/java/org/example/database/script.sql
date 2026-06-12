CREATE DATABASE Exercicio5;
USE Exercicio5;

CREATE TABLE Cursos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    carga_horaria INT NOT NULL
);

CREATE TABLE Alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Matriculas (
    curso_id INT NOT NULL,
    aluno_id INT NOT NULL,
    data_matricula DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (curso_id, aluno_id),
    FOREIGN KEY (curso_id) REFERENCES Cursos(id) ON DELETE CASCADE,
    FOREIGN KEY (aluno_id) REFERENCES Alunos(id) ON DELETE CASCADE
);