package br.com.desafio.dao;

import br.com.desafio.model.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PessoaDAOTest {
    private final PessoaDAO pessoaDAO = new PessoaDAO();

    @Test
    public void testeAdicionarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");
        pessoa.setIdade(30);
        pessoa.setSexo('M');
        pessoa.setDataEntrada(LocalDateTime.now());
        pessoaDAO.adicionarPessoa(pessoa);
        List<Pessoa> pessoas = pessoaDAO.listarPessoas();
        assertNotNull(pessoas);
        assertEquals(1, pessoas.size());
    }
}
