package br.com.desafio.dao;

import br.com.desafio.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    private final String url = "jdbc:mysql://localhost:3306/desab_enchentes";
    private final String usuario = "teste";
    private final String senha = "teste";

    public void adicionarProduto(Produto produto) throws SQLException {
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "INSERT INTO estoque (nome_produto, quantidade) VALUES (?, ?)";
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, produto.getNomeProduto());
                stmt.setDouble(2, produto.getQuantidade());
                stmt.executeUpdate();
            }
        }
    }

    public List<Produto> obterEstoque() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            String sql = "SELECT * FROM estoque";
            try (Statement stmt = conexao.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String nomeProduto = rs.getString("nome_produto");
                    double quantidade = rs.getDouble("quantidade");
                    produtos.add(new Produto(nomeProduto, quantidade));
                }
            }
        }
        return produtos;
    }
}
