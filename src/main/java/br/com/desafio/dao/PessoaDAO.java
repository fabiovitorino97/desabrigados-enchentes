package br.com.desafio.dao;

import br.com.desafio.model.Pessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.desafio.config.DatabaseConfig;


public class PessoaDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/desab_enchentes";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "123456";
    private Connection jdbcConnection;

    protected void connect() throws Exception {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws Exception {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean adicionarPessoa(Pessoa pessoa) throws Exception {
        String sql = "INSERT INTO usuarios (nome, idade, sexo, dataEntrada) VALUES (?, ?, ?, ?)";
        connect();

        boolean rowInserted;
        try (PreparedStatement statement = jdbcConnection.prepareStatement(sql)) {
            statement.setString(1, pessoa.getNome());
            statement.setInt(2, pessoa.getIdade());
            statement.setString(3, String.valueOf(pessoa.getSexo())); // Converte char para String
            statement.setString(4, pessoa.getDataEntrada());
            rowInserted = statement.executeUpdate() > 0;
        }
        disconnect();
        return rowInserted;
    }

    public List<Pessoa> listarPessoas() throws Exception {
        List<Pessoa> listaPessoas = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        connect();

        try (Statement statement = jdbcConnection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                char sexo = resultSet.getString("sexo").charAt(0); // Converte String para char
                String dataEntrada = resultSet.getString("dataEntrada");
                
                Pessoa pessoa = new Pessoa(5, "Maria", idade, "F", 2023-06-19);
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setIdade(idade);
                pessoa.setSexo(sexo);
                pessoa.setDataEntrada(dataEntrada);
                
                listaPessoas.add(pessoa);
            }
            
        }

        disconnect();

        return listaPessoas;
    }

    // Adicionar métodos de atualização e exclusão de usuários, se necessário

    public List<Pessoa> obterPessoas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
