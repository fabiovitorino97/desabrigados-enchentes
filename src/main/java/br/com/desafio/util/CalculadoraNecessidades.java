package br.com.desafio.util;

import br.com.desafio.model.Pessoa;

import java.util.List;

public class CalculadoraNecessidades {

    public static double calcularConsumoDiario(List<Pessoa> pessoas, String alimento) {
        double consumoTotal = 0;

        for (Pessoa pessoa : pessoas) {
            double consumoPorPessoa = 0;

            if (pessoa.getIdade() <= 3) {
                consumoPorPessoa = getConsumoPorAlimento(alimento, 50, 35, 15, 0);
            } else if (pessoa.getIdade() <= 8) {
                consumoPorPessoa = getConsumoPorAlimento(alimento, 50, 50, 25, 0);
            } else if (pessoa.getIdade() <= 12) {
                consumoPorPessoa = getConsumoPorAlimento(alimento, 50, 66, 33, 0);
            } else if (pessoa.getIdade() <= 18) {
                consumoPorPessoa = (pessoa.getSexo() == 'M') 
                    ? getConsumoPorAlimento(alimento, 50, 100, 50, 20) 
                    : getConsumoPorAlimento(alimento, 50, 83, 42, 20);
            } else if (pessoa.getIdade() <= 59) {
                consumoPorPessoa = (pessoa.getSexo() == 'M') 
                    ? getConsumoPorAlimento(alimento, 50, 100, 50, 30) 
                    : getConsumoPorAlimento(alimento, 50, 83, 42, 30);
            } else {
                consumoPorPessoa = getConsumoPorAlimento(alimento, 50, 66, 33, 20);
            }

            consumoTotal += consumoPorPessoa;
        }

        return consumoTotal;
    }

    private static double getConsumoPorAlimento(String alimento, double leitePo, double arroz, double feijao, double cafePo) {
        return switch (alimento) {
            case "LeitePo" -> leitePo;
            case "Arroz" -> arroz;
            case "Feijao" -> feijao;
            case "CafePo" -> cafePo;
            default -> 0;
        };
    }
}
