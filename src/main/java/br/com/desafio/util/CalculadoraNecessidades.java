package br.com.desafio.util;

import br.com.desafio.model.Pessoa;

import java.util.List;

public class CalculadoraNecessidades {
    public static double calcularConsumoDiario(List<Pessoa> pessoas, String alimento) {
        double consumoTotal = 0;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getIdade() <= 3) {
                consumoTotal += switch (alimento) {
                    case "LeitePo" -> 50;
                    case "Arroz" -> 35;
                    case "Feijao" -> 15;
                    default -> 0;
                };
            } else if (pessoa.getIdade() <= 8) {
                consumoTotal += switch (alimento) {
                    case "LeitePo" -> 50;
                    case "Arroz" -> 50;
                    case "Feijao" -> 25;
                    default -> 0;
                };
            } else if (pessoa.getIdade() <= 12) {
                consumoTotal += switch (alimento) {
                    case "LeitePo" -> 50;
                    case "Arroz" -> 66;
                    case "Feijao" -> 33;
                    default -> 0;
                };
            } else if (pessoa.getIdade() <= 18) {
                if (pessoa.getSexo() == 'M') {
                    consumoTotal += switch (alimento) {
                        case "LeitePo" -> 50;
                        case "Arroz" -> 100;
                        case "Feijao" -> 50;
                        case "CafePo" -> 20;
                        default -> 0;
                    };
                } else {
                    consumoTotal += switch (alimento) {
                        case "LeitePo" -> 50;
                        case "Arroz" -> 83;
                        case "Feijao" -> 42;
                        case "CafePo" -> 20;
                        default -> 0;
                    };
                }
            } else if (pessoa.getIdade() <= 59) {
                if (pessoa.getSexo() == 'M') {
                    consumoTotal += switch (alimento) {
                        case "LeitePo" -> 50;
                        case "Arroz" -> 100;
                        case "Feijao" -> 50;
                        case "CafePo" -> 30;
                        default -> 0;
                    };
                } else {
                    consumoTotal += switch (alimento) {
                        case "LeitePo" -> 50;
                        case "Arroz" -> 83;
                        case "Feijao" -> 42;
                        case "CafePo" -> 30;
                        default -> 0;
                    };
                }
            } else {
                consumoTotal += switch (alimento) {
                    case "LeitePo" -> 50;
                    case "Arroz" -> 66;
                    case "Feijao" -> 33;
                    case "CafePo" -> 20;
                    default -> 0;
                };
            }
        }

        return consumoTotal;
    }
}
