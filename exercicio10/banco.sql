-- Criação do banco de dados para o Sistema de Gerenciamento de Restaurante
CREATE DATABASE IF NOT EXISTS restaurante_db;
USE restaurante_db;

-- Tabela de Pratos
CREATE TABLE Pratos (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100)   NOT NULL,
    descricao VARCHAR(255),
    preco     DECIMAL(10,2)  NOT NULL
);

-- Tabela de Ingredientes
CREATE TABLE Ingredientes (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    nome           VARCHAR(100) NOT NULL,
    unidade_medida VARCHAR(20)  NOT NULL
);

-- Tabela Receitas (N:N entre Pratos e Ingredientes)
CREATE TABLE Receitas (
    prato_id              INT            NOT NULL,
    ingrediente_id        INT            NOT NULL,
    quantidade_necessaria DECIMAL(10,2)  NOT NULL,
    PRIMARY KEY (prato_id, ingrediente_id),
    FOREIGN KEY (prato_id)       REFERENCES Pratos(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);

-- Dados de exemplo
INSERT INTO Pratos (nome, descricao, preco) VALUES
    ('Macarrão à Bolonhesa', 'Macarrão com molho de carne moída',      35.90),
    ('Frango Grelhado',      'Frango grelhado com legumes',            42.50),
    ('Salada Caesar',        'Salada com alface, croutons e molho caesar', 28.00);

INSERT INTO Ingredientes (nome, unidade_medida) VALUES
    ('Macarrão',        'gramas'),
    ('Carne Moída',     'gramas'),
    ('Molho de Tomate', 'ml'),
    ('Frango',          'gramas'),
    ('Azeite',          'ml'),
    ('Alface',          'folhas'),
    ('Croutons',        'gramas'),
    ('Molho Caesar',    'ml');

INSERT INTO Receitas (prato_id, ingrediente_id, quantidade_necessaria) VALUES
    (1, 1, 200.0),
    (1, 2, 150.0),
    (1, 3, 100.0),
    (2, 4, 300.0),
    (2, 5,  30.0),
    (3, 6,   5.0),
    (3, 7,  50.0),
    (3, 8,  40.0);

-- Consulta: ingredientes do prato 1
SELECT i.nome, i.unidade_medida, r.quantidade_necessaria
FROM Receitas r
INNER JOIN Ingredientes i ON r.ingrediente_id = i.id
WHERE r.prato_id = 1;