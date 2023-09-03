package com.wepet.classes;

public class Produto {
    int id;
    String nome;
    String descricao;
    double preco;

    public Produto(String nome, String descricao, double preco, int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
    }

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String toJSON() {
        String resultado = "{";

        resultado = resultado + "\"nome\": \"" + this.nome + "\",";
        resultado = resultado + "\"descricao\": \"" + this.descricao + "\",";
        resultado = resultado + "\"id\": " + Integer.toString(this.id) + ",";
        resultado = resultado + "\"preco\": " + Double.toString(this.preco) + "}";

        return resultado;
    }

}
