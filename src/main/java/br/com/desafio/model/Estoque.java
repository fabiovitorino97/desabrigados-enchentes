package br.com.desafio.model;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<String, Double> produtos;

    public Estoque() {
        produtos = new HashMap<>();
    }

    public void adicionarProduto(String nomeProduto, double quantidade) {
        produtos.put(nomeProduto, produtos.getOrDefault(nomeProduto, 0.0) + quantidade);
    }

    public void removerProduto(String nomeProduto, double quantidade) {
        if (produtos.containsKey(nomeProduto)) {
            double quantidadeAtual = produtos.get(nomeProduto);
            if (quantidadeAtual >= quantidade) {
                produtos.put(nomeProduto, quantidadeAtual - quantidade);
            } else {
                System.out.println("Quantidade insuficiente para remover.");
            }
        } else {
            System.out.println("Produto não encontrado no estoque.");
        }
    }

    public Map<String, Double> getProdutos() {
        return produtos;
    }

    public double getQuantidade(String nomeProduto) {
        return produtos.getOrDefault(nomeProduto, 0.0);
    }

    public double getArroz() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getFeijao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getLeitePo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public double getCafePo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
