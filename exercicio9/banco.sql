    -- Criação do banco de dados para o Sistema de Consultas Médicas
    CREATE DATABASE IF NOT EXISTS clinica_db;
    USE clinica_db;

    -- Tabela de Pacientes
    CREATE TABLE Pacientes (
        id               INT AUTO_INCREMENT PRIMARY KEY,
        nome             VARCHAR(100) NOT NULL,
        data_nascimento  DATE         NOT NULL,
        telefone         VARCHAR(20),
        endereco         VARCHAR(255)
    );

    -- Tabela de Médicos
    CREATE TABLE Medicos (
        id             INT AUTO_INCREMENT PRIMARY KEY,
        nome           VARCHAR(100) NOT NULL,
        especialidade  VARCHAR(100) NOT NULL,
        crm            VARCHAR(20)  NOT NULL UNIQUE
    );

    -- Tabela de Consultas
    CREATE TABLE Consultas (
        id          INT AUTO_INCREMENT PRIMARY KEY,
        paciente_id INT          NOT NULL,
        medico_id   INT          NOT NULL,
        data_hora   DATETIME     NOT NULL,
        status      VARCHAR(50)  NOT NULL DEFAULT 'agendada',
        observacoes TEXT,
        FOREIGN KEY (paciente_id) REFERENCES Pacientes(id),
        FOREIGN KEY (medico_id)   REFERENCES Medicos(id)
    );

    -- Dados de exemplo
    INSERT INTO Pacientes (nome, data_nascimento, telefone, endereco) VALUES
        ('João Silva',    '1990-05-15', '11999990001', 'Rua A, 123'),
        ('Maria Santos',  '1985-08-22', '11999990002', 'Rua B, 456'),
        ('Pedro Oliveira','2000-03-10', '11999990003', 'Rua C, 789');

    INSERT INTO Medicos (nome, especialidade, crm) VALUES
        ('Dr. Carlos Lima',   'Cardiologia', 'CRM12345'),
        ('Dra. Ana Ferreira', 'Pediatria',   'CRM67890'),
        ('Dr. Paulo Mendes',  'Ortopedia',   'CRM11223');

    INSERT INTO Consultas (paciente_id, medico_id, data_hora, status, observacoes) VALUES
        (1, 1, '2025-09-15 09:00:00', 'agendada', 'Consulta de rotina'),
        (2, 1, '2025-09-15 10:30:00', 'agendada', 'Checkup cardíaco'),
        (3, 2, '2025-09-15 14:00:00', 'agendada', 'Consulta pediátrica'),
        (1, 3, '2025-09-16 08:00:00', 'agendada', 'Dor no joelho');

    -- Consulta: consultas de um médico específico em uma data
    SELECT p.nome AS paciente, m.nome AS medico,
           c.data_hora, c.status, c.observacoes
    FROM Consultas c
    INNER JOIN Pacientes p ON c.paciente_id = p.id
    INNER JOIN Medicos   m ON c.medico_id   = m.id
    WHERE c.medico_id = 1 AND DATE(c.data_hora) = '2025-09-15';