/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.desafio.config;
/**
 *
 * @author Fabio
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    // Configurações de conexão com o banco de dados
    private static final String DB_URL = "jdbc:mysql://localhost:3306/desab_enchentes";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    // Método para obter uma conexão com o banco de dados
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Método para criar tabelas no banco de dados
    public void createTables() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            // SQL para criar tabelas
            String sqlCreateTables = "CREATE TABLE IF NOT EXISTS pessoas (" +
                    "id_usuario INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nome VARCHAR(255) NOT NULL, " +
                    "idade INT NOT NULL, " +
                    "sexo CHAR(1) NOT NULL, " +
                    "dataEntrada DATE NOT NULL" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS produtos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nomeProduto VARCHAR(255) NOT NULL, " +
                    "quantidade INT NOT NULL" +
                    ");" +
                    "CREATE TABLE IF NOT EXISTS estoque (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "arroz INT NOT NULL, " +
                    "feijao INT NOT NULL, " +
                    "leitePo INT NOT NULL, " +
                    "cafePo INT NOT NULL" +
                    ");";
            
            // Executar a criação das tabelas
            statement.executeUpdate(sqlCreateTables);
        } catch (SQLException e) {
            // Tratamento de erro em caso de falha na criação das tabelas
            System.err.println(e.getMessage());
            System.err.println("Falha ao criar tabelas no banco de dados.");
        }
    }
}
