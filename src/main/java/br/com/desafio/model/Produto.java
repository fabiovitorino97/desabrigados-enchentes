package br.com.desafio.model;

public class Produto {
    private String nomeProduto;
    private double quantidade;

    public Produto(String nomeProduto, double quantidade) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
