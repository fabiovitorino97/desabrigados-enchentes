package br.com.desafio.main;

import br.com.desafio.dao.EstoqueDAO;
import br.com.desafio.dao.PessoaDAO;
import br.com.desafio.model.Pessoa;
import br.com.desafio.model.Produto;
import br.com.desafio.config.DatabaseConfig;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();

        try {
            // Adicionar produtos ao estoque
            estoqueDAO.adicionarProduto(new Produto("Arroz", 1000));
            estoqueDAO.adicionarProduto(new Produto("Feij�o", 500));

            // Obter produtos do estoque
            List<Produto> produtos = estoqueDAO.obterEstoque();
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getNomeProduto() + ", Quantidade: " + produto.getQuantidade());
            }
            int idade = 0;

            // Adicionar pessoas
            pessoaDAO.adicionarPessoa(new Pessoa(30, "Jo�o", idade, "2023-06-19", 2023 - 6 - 19)); // Ajustado para dataEntrada
            pessoaDAO.adicionarPessoa(new Pessoa(5, "Maria", idade, "2023-06-19", 2023 - 6 - 19));

            // Obter pessoas
            List<Pessoa> pessoas = pessoaDAO.obterPessoas();
            for (Pessoa pessoa : pessoas) {
                System.out.println("Pessoa: " + pessoa.getNome() + ", Idade: " + pessoa.getIdade() + ", Sexo: " + pessoa.getSexo() + ", Data de Entrada: " + pessoa.getDataEntrada());
            }

        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
