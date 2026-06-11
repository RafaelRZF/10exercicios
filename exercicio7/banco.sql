-- Criação do banco de dados para o Sistema de Eventos e Participantes
CREATE DATABASE IF NOT EXISTS eventos_db;
USE eventos_db;

-- Tabela de Eventos
CREATE TABLE Eventos (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    data_evento DATE         NOT NULL,
    local       VARCHAR(100),
    descricao   VARCHAR(255)
);

-- Tabela de Participantes
CREATE TABLE Participantes (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);

-- Tabela associativa Inscricoes (N:N entre Eventos e Participantes)
CREATE TABLE Inscricoes (
    evento_id       INT         NOT NULL,
    participante_id INT         NOT NULL,
    data_inscricao  DATE        NOT NULL,
    status_pagamento VARCHAR(20) NOT NULL DEFAULT 'pendente',
    PRIMARY KEY (evento_id, participante_id),
    FOREIGN KEY (evento_id)       REFERENCES Eventos(id),
    FOREIGN KEY (participante_id) REFERENCES Participantes(id)
);

-- Índices para melhorar o desempenho das consultas
CREATE INDEX idx_inscricoes_evento      ON Inscricoes(evento_id);
CREATE INDEX idx_inscricoes_participante ON Inscricoes(participante_id);
CREATE INDEX idx_inscricoes_status      ON Inscricoes(status_pagamento);

-- Dados de exemplo
INSERT INTO Eventos (nome, data_evento, local, descricao) VALUES
    ('Conferência de TI',          '2025-08-15', 'São Paulo',      'Evento sobre tecnologia'),
    ('Workshop de Java',           '2025-09-10', 'Rio de Janeiro', 'Workshop prático de Java'),
    ('Seminário de Banco de Dados','2025-10-05', 'Curitiba',       'Seminário sobre SQL e NoSQL');

INSERT INTO Participantes (nome, email, telefone) VALUES
    ('Ana Silva',      'ana@email.com',    '11999990001'),
    ('Carlos Souza',   'carlos@email.com', '11999990002'),
    ('Maria Oliveira', 'maria@email.com',  '11999990003'),
    ('Pedro Costa',    'pedro@email.com',  '11999990004');

INSERT INTO Inscricoes (evento_id, participante_id, data_inscricao, status_pagamento) VALUES
    (1, 1, CURDATE(), 'pago'),
    (1, 2, CURDATE(), 'pendente'),
    (1, 3, CURDATE(), 'pago'),
    (2, 1, CURDATE(), 'pago'),
    (2, 4, CURDATE(), 'pendente'),
    (3, 2, CURDATE(), 'pago');

-- Consulta: participantes pagos de um evento específico (evento 1)
SELECT p.nome, p.email
FROM Participantes p
INNER JOIN Inscricoes i ON p.id = i.participante_id
WHERE i.evento_id = 1 AND i.status_pagamento = 'pago';