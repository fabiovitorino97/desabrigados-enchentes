package repositories.impl;

import br.com.desafio.config.DatabaseConfig;
import br.com.desafio.model.Pessoa;
import repositories.PessoaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PessoaRepositoryImpl implements PessoaRepository {
    private final DatabaseConfig databaseConfig;

    public PessoaRepositoryImpl(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    @Override
    public Pessoa insert(Pessoa pessoa) {
        String sql_insert = "INSERT INTO pessoas (nome, idade, sexo, dataEntrada) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS)){
            setPessoa(pessoa, statement);
            affectedRowsVerify(statement, "Failed to save pessoa, no rows affected.");
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                pessoa.setIdUsuario(generatedId);
            } else {
                throw new SQLException("Failed to save pessoa, no ID obtained.");
            }
            generatedKeys.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return pessoa;
    }

    @Override
    public List<Pessoa> selectAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql_selectAll = "SELECT * FROM pessoas";
        try (Connection connection = databaseConfig.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql_selectAll)) {
            while (resultSet.next()) {
                Pessoa pessoa = getPessoa(resultSet);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return pessoas;
    }

    @Override
    public Optional<Pessoa> selectById(Integer id) {
        String sql_selectById = "SELECT * FROM pessoas WHERE id_usuario = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql_selectById)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Pessoa pessoa = getPessoa(resultSet);
                return Optional.of(pessoa);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        String sql_update = "UPDATE pessoas SET nome = ?, idade = ?, sexo = ?, dataEntrada = ? WHERE id_usuario = ?";
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql_update)) {
            setPessoa(pessoa, statement);
            statement.setInt(5, pessoa.getIdUsuario());
            affectedRowsVerify(statement, "Failed to update pessoa, no rows affected.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return pessoa;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = databaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM pessoas WHERE id_usuario = ?")) {
            statement.setInt(1, id);
            affectedRowsVerify(statement, "Failed to remove pessoa, no rows affected.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private Pessoa getPessoa(ResultSet resultSet) throws SQLException {
        int idUsuario = resultSet.getInt("id_usuario");
        String nome = resultSet.getString("nome");
        int idade = resultSet.getInt("idade");
        String sexo = resultSet.getString("sexo");
        LocalDate dataEntrada = resultSet.getDate("dataEntrada").toLocalDate();
        return new Pessoa(idUsuario, nome, idade, sexo, 2023 - 6 - 19);
    }

    private void setPessoa(Pessoa pessoa, PreparedStatement statement) throws SQLException {
    statement.setString(1, pessoa.getNome());
    statement.setInt(2, pessoa.getIdade());
    statement.setString(3, String.valueOf(pessoa.getSexo()));  // Corrigido para setString para sexo
    // Verifica se a data de entrada é nula antes de tentar definir o valor no PreparedStatement
    if (pessoa.getDataEntrada() != null) {
        statement.setDate(4, Date.valueOf(pessoa.getDataEntrada()));
    } else {
        statement.setNull(4, java.sql.Types.DATE);
    }
}



    private void affectedRowsVerify(PreparedStatement statement, String message) throws SQLException {
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException(message);
        }
    }
}
