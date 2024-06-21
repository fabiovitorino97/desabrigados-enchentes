package br.com.desafio.dao;

import br.com.desafio.model.Pessoa;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PessoaDAOTeste {
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    
    @Test
    public void testeAdicionarPessoa(int idade) throws SQLException, Exception {
        Pessoa pessoa = new Pessoa(5, "Maria", idade, "2023-06-19", 2023 - 6 - 19);
        pessoa.setNome("Teste");
        pessoa.setIdade(30);
        pessoa.setSexo('M');
        pessoa.setDataEntrada("20/06/2024");
        pessoaDAO.adicionarPessoa(pessoa);
        List<Pessoa> pessoas = pessoaDAO.listarPessoas();
        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
    }
}
