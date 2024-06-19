CREATE DATABASE desabrigados;
USE desabrigados;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
    sexo ENUM('M', 'F'),
    data_entrada TIMESTAMP,
    data_saida TIMESTAMP
);

CREATE TABLE estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    arroz DOUBLE,
    feijao DOUBLE,
    leite_po DOUBLE,
    cafe_po DOUBLE,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE produto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    quantidade DOUBLE,
    unidade VARCHAR(20)
);
