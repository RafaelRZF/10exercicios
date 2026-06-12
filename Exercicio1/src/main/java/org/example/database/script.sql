CREATE DATABASE Exercicio1;
USE Exercicio1;

CREATE TABLE Autor(
	id INT auto_increment PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(100) NOT NULL
);

CREATE TABLE Editora(
	id INT auto_increment PRIMARY KEY NOT NULL,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);

CREATE TABLE Livro(
	id INT auto_increment PRIMARY KEY NOT NULL,
    editora_id INT NOT NULL,
    autor_id INT NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    ano_publicacao VARCHAR(5),
    FOREIGN KEY (editora_id) REFERENCES Editora(id),
    FOREIGN KEY (autor_id) REFERENCES Autor(id)
)