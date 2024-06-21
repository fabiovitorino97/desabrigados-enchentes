/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.desafio.dao;

/**
 *
 * @author Fabio
 */
import br.com.desafio.config.DatabaseConfig;
import br.com.desafio.model.Estoque;
import br.com.desafio.model.Pessoa;
import br.com.desafio.model.Produto;
import br.com.desafio.util.CalculadoraNecessidades;
import br.com.desafio.util.PrevisaoEstoque;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EstoqueDAOTeste {

    private EstoqueDAO estoqueDAO;
    private PessoaDAO pessoaDAO;
    private int idade;

    @BeforeEach
    public void setUp() {
        new DatabaseConfig();
        estoqueDAO = new EstoqueDAO();
        pessoaDAO = new PessoaDAO();
    }

    @Test
    public void testAdicionarProduto() {
        Produto produto = new Produto("Arroz", 1000);
        try {
            estoqueDAO.adicionarProduto(produto);
            List<Produto> produtos = estoqueDAO.obterEstoque();
            assertTrue(produtos.contains(produto));
        } catch (SQLException e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }

    @Test
    public void testObterEstoque() {
        try {
            List<Produto> produtos = estoqueDAO.obterEstoque();
            assertNotNull(produtos);
        } catch (SQLException e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }

    @Test
    public void testAdicionarPessoa() throws Exception {
        Pessoa pessoa = new Pessoa(30, "Joao", idade, "M", 2023 - 6 - 19);
        try {
            pessoaDAO.adicionarPessoa(pessoa);
            List<Pessoa> pessoas = pessoaDAO.obterPessoas();
            assertTrue(pessoas.contains(pessoa));
        } catch (SQLException e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }

    @Test
    public void testObterPessoas() {
        List<Pessoa> pessoas = pessoaDAO.obterPessoas();
        assertNotNull(pessoas);
    }

    @Test
    public void testCalcularConsumoDiario() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(30, "Joao", idade, "M", 2023 - 6 - 19));
        pessoas.add(new Pessoa(5, "Maria", idade, "F", 2023 - 6 - 19));
        
        double consumoArroz = CalculadoraNecessidades.calcularConsumoDiario(pessoas, "Arroz");
        assertEquals(100 + 35, consumoArroz); // Verifica o consumo total de arroz para um adulto e uma criança
    }

    @Test
    public void testCalcularDiasDuracao() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(30, "Joao", idade, "M", 2023 - 6 - 19));
        pessoas.add(new Pessoa(5, "Maria", idade, "F", 2023 - 6 - 19));

        try {
            // Adiciona produtos ao estoque
            estoqueDAO.adicionarProduto(new Produto("Arroz", 1000));
            estoqueDAO.adicionarProduto(new Produto("Feijão", 500));
            estoqueDAO.adicionarProduto(new Produto("LeitePo", 200));
            estoqueDAO.adicionarProduto(new Produto("CafePo", 100));

            // Obtém a lista de produtos do estoque
            List<Produto> estoque = estoqueDAO.obterEstoque();

            // Converte a lista de produtos em um objeto Estoque
            Estoque estoqueGeral = new Estoque(
                estoque.stream().filter(p -> p.getNomeProduto().equals("Arroz")).findFirst().orElse(new Produto("Arroz", 0)).getQuantidade(),
                estoque.stream().filter(p -> p.getNomeProduto().equals("Feijão")).findFirst().orElse(new Produto("Feijão", 0)).getQuantidade(),
                estoque.stream().filter(p -> p.getNomeProduto().equals("LeitePo")).findFirst().orElse(new Produto("LeitePo", 0)).getQuantidade(),
                estoque.stream().filter(p -> p.getNomeProduto().equals("CafePo")).findFirst().orElse(new Produto("CafePo", 0)).getQuantidade()
            );

            // Calcula os dias de duração do estoque com base nas necessidades das pessoas
            int diasDuracao = PrevisaoEstoque.calcularDiasDuracao(estoqueGeral, pessoas);

            // Verifica se os dias de duração são maiores que zero
            assertTrue(diasDuracao > 0);
        } catch (SQLException e) {
            fail("Exception thrown during test: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        // Limpeza e reset do banco de dados, se necessário
    }
}
