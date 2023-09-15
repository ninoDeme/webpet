package com.webpet.classes;

import org.json.JSONObject;

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
        JSONObject resultado = new JSONObject();

        resultado.put("nome", this.nome );
        resultado.put("descricao", this.descricao );
        resultado.put("id", this.id);
        resultado.put("imagem", this.imagem);

        return resultado.toString();
    }

}
