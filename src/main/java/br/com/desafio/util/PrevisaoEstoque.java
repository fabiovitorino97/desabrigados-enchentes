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

        double diasDuracaoArroz = estoque.getArroz() / consumoDiarioArroz;
        double diasDuracaoFeijao = estoque.getFeijao() / consumoDiarioFeijao;
        double diasDuracaoLeitePo = estoque.getLeitePo() / consumoDiarioLeitePo;
        double diasDuracaoCafePo = estoque.getCafePo() / consumoDiarioCafePo;

        return (int) Math.min(Math.min(diasDuracaoArroz, diasDuracaoFeijao), Math.min(diasDuracaoLeitePo, diasDuracaoCafePo));
    }
}
