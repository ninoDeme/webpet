package com.webpet.classes;

import org.json.JSONObject;

public class Categoria {
    int id;
    String nome;
    String imagem;

    public Categoria(String nome, String imagem,int id) {
        this.nome = nome;
        this.imagem = imagem;
        this.id = id;
    }

    public Categoria(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
       
    }

    public String toJSON() {
        JSONObject resultado = new JSONObject();

        resultado.put("nome", this.nome );
        resultado.put("id", this.id);
        resultado.put("imagem", this.imagem);

        return resultado.toString();
    }

}

    

