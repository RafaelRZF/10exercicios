CREATE DATABASE Exercicio2;
USE Exercicio2;

CREATE TABLE Clientes (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) UNIQUE NOT NULL,
                          endereco VARCHAR(255)
);

CREATE TABLE Produtos (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(100) NOT NULL,
                          preco DECIMAL(10, 2) NOT NULL,
                          estoque INT NOT NULL
);

CREATE TABLE Pedidos (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         cliente_id INT NOT NULL,
                         data_pedido DATE NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);
-- 4. Criação da tabela Intermediária: ItensPedido (Muitos para Muitos)
CREATE TABLE ItensPedido (
                             pedido_id INT NOT NULL,
                             produto_id INT NOT NULL,
                             quantidade INT NOT NULL,
                             preco_unitario DECIMAL(10, 2) NOT NULL,
    -- A chave primária é composta pelos dois IDs juntos
                             CONSTRAINT PK_ItensPedido PRIMARY KEY (pedido_id, produto_id),
                             FOREIGN KEY (pedido_id) REFERENCES Pedidos(id),
                             FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);