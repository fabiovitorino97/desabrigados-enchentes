package br.com.desafio.dao;

import br.com.desafio.model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private final String url = "jdbc:mysql://localhost:3306/desabrigados_enchentes";
    private final String usuario = "teste";
    private final String senha = "teste";

    public void adicionarPessoa(Pessoa pessoa) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO pessoa (nome, idade, sexo, categoria_etaria) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, pessoa.getNome());
                stmt.setInt(2, pessoa.getIdade());
                stmt.setString(3, pessoa.getSexo());
                stmt.setString(4, pessoa.getCategoriaEtaria());
                stmt.executeUpdate();
            }
        }
    }

    public List<Pessoa> obterPessoas() throws SQLException {
        List<Pessoa> pessoas = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM pessoa";
            try (Statement stmt = conexao.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    String sexo = rs.getString("sexo");
                    String categoriaEtaria = rs.getString("categoria_etaria");
                    pessoas.add(new Pessoa(nome, idade, sexo, categoriaEtaria));
                }
            }
        }
        return pessoas;
    }
}
