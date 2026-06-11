CREATE DATABASE Exercicio3;
USE Exercicio3;


CREATE TABLE Usuarios (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
);


CREATE TABLE Projetos (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          descricao TEXT,
                          data_inicio DATE NOT NULL,
                          data_fim DATE,
);


CREATE TABLE Tarefas (
                         id INT AUTO_INCREMENT,
                         projeto_id INT NOT NULL PRIMARY KEY,
                         usuario_id INT NOT NULL,
                         descricao VARCHAR(255) NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         data_vencimento DATE NOT NULL,
                         FOREIGN KEY (projeto_id) REFERENCES Projetos(id),
                         FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);