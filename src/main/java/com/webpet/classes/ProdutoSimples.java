package com.webpet.classes;

public class ProdutoSimples {
    int id;
    String nome;
    String descricao;
    double preco;
    int quantidade;

    public ProdutoSimples(String nome, String descricao, double preco, int id, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
        this.quantidade=quantidade;
    }

    public ProdutoSimples(String nome, String descricao, double preco,int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade=quantidade;
    }

    public String toJSON() {
        String resultado = "{";

        resultado += "\"nome\": \"" + this.nome + "\",";
        resultado += "\"descricao\": \"" + this.descricao + "\",";
        resultado += "\"id\": " + Integer.toString(this.id) + ",";
        resultado += "\"preco\": " + Double.toString(this.preco);
        resultado += "\"quantidade\": " + Integer.toString(this.quantidade);
        
        resultado += "}";

        return resultado;
    }

}
