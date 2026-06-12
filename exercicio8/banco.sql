-- Criação do banco de dados para o Sistema de Avaliação de Filmes
CREATE DATABASE IF NOT EXISTS filmes_db;
USE filmes_db;

-- Tabela de Usuários
CREATE TABLE Usuarios (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Tabela de Filmes
CREATE TABLE Filmes (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    titulo          VARCHAR(150) NOT NULL,
    ano_lancamento  INT          NOT NULL,
    diretor         VARCHAR(100),
    genero          VARCHAR(50)
);

-- Tabela de Avaliações
CREATE TABLE Avaliacoes (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id     INT  NOT NULL,
    filme_id       INT  NOT NULL,
    nota           INT  NOT NULL CHECK (nota BETWEEN 1 AND 10),
    comentario     TEXT,
    data_avaliacao DATE NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (filme_id)   REFERENCES Filmes(id)
);

-- Dados de exemplo
INSERT INTO Usuarios (nome, email) VALUES
    ('Ana Silva',    'ana@email.com'),
    ('Carlos Souza', 'carlos@email.com'),
    ('Maria Lima',   'maria@email.com');

INSERT INTO Filmes (titulo, ano_lancamento, diretor, genero) VALUES
    ('O Poderoso Chefão', 1972, 'Francis Ford Coppola', 'Drama'),
    ('Interestelar',      2014, 'Christopher Nolan',    'Ficção Científica'),
    ('Parasita',          2019, 'Bong Joon-ho',         'Drama');

INSERT INTO Avaliacoes (usuario_id, filme_id, nota, comentario, data_avaliacao) VALUES
    (1, 1, 10, 'Obra-prima do cinema!',          CURDATE()),
    (2, 1,  9, 'Incrível, clássico absoluto.',   CURDATE()),
    (3, 1,  8, 'Muito bom, mas longo.',           CURDATE()),
    (1, 2, 10, 'Visualmente deslumbrante!',       CURDATE()),
    (2, 2,  8, 'Roteiro complexo mas envolvente.',CURDATE());

-- Consulta: média e comentários de um filme específico (filme 1)
SELECT f.titulo, AVG(a.nota) AS media_nota
FROM Filmes f
INNER JOIN Avaliacoes a ON f.id = a.filme_id
WHERE f.id = 1
GROUP BY f.id, f.titulo;

SELECT u.nome, a.nota, a.comentario, a.data_avaliacao
FROM Avaliacoes a
INNER JOIN Usuarios u ON a.usuario_id = u.id
WHERE a.filme_id = 1
ORDER BY a.data_avaliacao DESC;