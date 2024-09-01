package com.loja.model;

public class Loja {
    private int id;
    private String nome;
    private int quantidadeItens;
    private double precoMedio;
    private static final double MARGEM_LUCRO = 0.20; // Margem de lucro de 20%

    // Construtores
    public Loja() {}

    public Loja(int id, String nome, int quantidadeItens, double precoMedio) {
        this.id = id;
        this.nome = nome;
        this.quantidadeItens = quantidadeItens;
        this.precoMedio = precoMedio;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio = precoMedio;
    }

    // Método para calcular o lucro
    public double calcularLucro() {
        return quantidadeItens * precoMedio * MARGEM_LUCRO;
    }

    public static double getMargemLucro() {
		return MARGEM_LUCRO;
	}

	// Método para adicionar itens ao estoque
    public void adicionarItemEstoque(int quantidade) {
        this.quantidadeItens += quantidade;
    }

    // Método para atualizar o preço médio
    public void atualizarPreco(double novoPreco) {
        this.precoMedio = novoPreco;
    }
}
