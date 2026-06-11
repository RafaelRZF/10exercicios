-- Criação do banco de dados para o Sistema de Inventário de Loja
CREATE DATABASE IF NOT EXISTS inventario_loja;
USE inventario_loja;

-- Tabela de Fornecedores
CREATE TABLE Fornecedores (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    contato  VARCHAR(100),
    telefone VARCHAR(20)
);

-- Tabela de Produtos
CREATE TABLE Produtos (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    descricao     VARCHAR(255),
    preco_compra  DECIMAL(10,2) NOT NULL,
    preco_venda   DECIMAL(10,2) NOT NULL,
    fornecedor_id INT,
    FOREIGN KEY (fornecedor_id) REFERENCES Fornecedores(id)
);

-- Tabela de Transações de Estoque
CREATE TABLE TransacoesEstoque (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    produto_id       INT NOT NULL,
    tipo_transacao   ENUM('entrada','saida') NOT NULL,
    quantidade       INT NOT NULL,
    data_transacao   DATETIME NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);

-- Dados de exemplo
INSERT INTO Fornecedores (nome, contato, telefone) VALUES
    ('Fornecedor Alpha', 'Carlos', '11999990001'),
    ('Fornecedor Beta',  'Ana',    '11999990002');

INSERT INTO Produtos (nome, descricao, preco_compra, preco_venda, fornecedor_id) VALUES
    ('Notebook', 'Notebook i5 8GB',  2500.00, 3800.00, 1),
    ('Mouse',    'Mouse sem fio',       45.00,   89.00, 1),
    ('Teclado',  'Teclado mecânico',   120.00,  220.00, 2);

INSERT INTO TransacoesEstoque (produto_id, tipo_transacao, quantidade, data_transacao) VALUES
    (1, 'entrada', 50, NOW()),
    (1, 'saida',   10, NOW()),
    (2, 'entrada', 100, NOW()),
    (3, 'entrada',  30, NOW()),
    (3, 'saida',     5, NOW());

-- Consulta: estoque atual de cada produto
SELECT p.nome,
       SUM(CASE WHEN t.tipo_transacao = 'entrada' THEN t.quantidade ELSE 0 END) -
       SUM(CASE WHEN t.tipo_transacao = 'saida'   THEN t.quantidade ELSE 0 END) AS estoque_atual
FROM Produtos p
LEFT JOIN TransacoesEstoque t ON p.id = t.produto_id
GROUP BY p.id, p.nome;