package br.com.desafio.main;

import br.com.desafio.dao.EstoqueDAO;
import br.com.desafio.dao.PessoaDAO;
import br.com.desafio.model.Pessoa;
import br.com.desafio.model.Produto;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EstoqueDAO estoqueDAO = new EstoqueDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();

        try {
            // Adicionar produtos ao estoque
            estoqueDAO.adicionarProduto(new Produto("Arroz", 1000));
            estoqueDAO.adicionarProduto(new Produto("Feijão", 500));

            // Obter produtos do estoque
            List<Produto> produtos = estoqueDAO.obterEstoque();
            for (Produto produto : produtos) {
                System.out.println("Produto: " + produto.getNomeProduto() + ", Quantidade: " + produto.getQuantidade());
            }

            // Adicionar pessoas
            pessoaDAO.adicionarPessoa(new Pessoa("João", 30, "M", "Adulto"));
            pessoaDAO.adicionarPessoa(new Pessoa("Maria", 5, "F", "Criança"));

            // Obter pessoas
            List<Pessoa> pessoas = pessoaDAO.obterPessoas();
            for (Pessoa pessoa : pessoas) {
                System.out.println("Pessoa: " + pessoa.getNome() + ", Idade: " + pessoa.getIdade() + ", Sexo: " + pessoa.getSexo() + ", Categoria: " + pessoa.getCategoriaEtaria());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
