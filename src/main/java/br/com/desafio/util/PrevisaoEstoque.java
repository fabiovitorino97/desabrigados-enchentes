package br.com.desafio.util;

import br.com.desafio.model.Estoque;
import br.com.desafio.model.Pessoa;

import java.util.List;

public class PrevisaoEstoque {

    public static int calcularDiasDuracao(Estoque estoque, List<Pessoa> pessoas) {
        double consumoDiarioArroz = CalculadoraNecessidades.calcularConsumoDiario(pessoas, "Arroz");
        double consumoDiarioFeijao = CalculadoraNecessidades.calcularConsumoDiario(pessoas, "Feijao");
        double consumoDiarioLeitePo = CalculadoraNecessidades.calcularConsumoDiario(pessoas, "LeitePo");
        double consumoDiarioCafePo = CalculadoraNecessidades.calcularConsumoDiario(pessoas, "CafePo");

        // Prevenção de divisão por zero
        double diasDuracaoArroz = consumoDiarioArroz != 0 ? estoque.getArroz() / consumoDiarioArroz : Double.POSITIVE_INFINITY;
        double diasDuracaoFeijao = consumoDiarioFeijao != 0 ? estoque.getFeijao() / consumoDiarioFeijao : Double.POSITIVE_INFINITY;
        double diasDuracaoLeitePo = consumoDiarioLeitePo != 0 ? estoque.getLeitePo() / consumoDiarioLeitePo : Double.POSITIVE_INFINITY;
        double diasDuracaoCafePo = consumoDiarioCafePo != 0 ? estoque.getCafePo() / consumoDiarioCafePo : Double.POSITIVE_INFINITY;

        return (int) Math.min(Math.min(diasDuracaoArroz, diasDuracaoFeijao), Math.min(diasDuracaoLeitePo, diasDuracaoCafePo));
    }
}
