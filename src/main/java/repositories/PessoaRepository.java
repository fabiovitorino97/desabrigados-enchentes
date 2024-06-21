/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

/**
 *
 * @author Fabio
 */
import br.com.desafio.model.Pessoa;
import java.util.List;
import java.util.Optional;

public interface PessoaRepository {
    Pessoa insert(Pessoa pessoa);
    List<Pessoa> selectAll();
    Optional<Pessoa> selectById(Integer id);
    Pessoa update(Pessoa pessoa);
    void deleteById(Integer id);
}
