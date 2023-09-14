package com.webpet.classes;

public class Animal {
    int id;
    String nome;
    String descricao;
    String imagem;

    public Animal(String nome, String descricao,String imagem, int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id = id;
    }

    public Animal(String nome, String descricao, String imagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public String toJSON() {
        String resultado = "{";

        resultado += "\"nome\": \"" + this.nome + "\",";
        resultado += "\"descricao\": \"" + this.descricao + "\",";
        resultado += "\"id\": " + Integer.toString(this.id) + ",";
        resultado += "\"imagem\": " + this.imagem;
        
        resultado += "}";

        return resultado;
    }

}
