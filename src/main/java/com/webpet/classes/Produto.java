package com.webpet.classes;

import org.json.JSONObject;

public class Produto {

    String nome;
    String descricao;
    double preco;
    int id;
    int quantidade;
    int id_categoria;
    int id_animal;
    Animal animal= null;
    Categoria categoria = null;
    String imagem;
    Double peso;
    String detalhes;

    public Produto(String nome, String descricao, double preco, int id, int quantidade, Categoria categoria,
            Animal animal, String imagem, Double peso, String detalhes) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.animal = animal;
        this.imagem = imagem;
        this.peso = peso;
        this.detalhes = detalhes;
    }

    public Produto(String nome, String descricao, double preco, int id, int quantidade, int id_categoria, int id_animal,
            String imagem, Double peso, String detalhes) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
        this.quantidade = quantidade;
        this.id_categoria = id_categoria;
        this.id_animal = id_animal;
        this.imagem = imagem;
        this.peso = peso;
        this.detalhes = detalhes;
    }

    public String toJSON() {
        return this.toJSON(false);
    }
    public String toJSON(boolean completo) {
        JSONObject resultado = new JSONObject();

        resultado.put("nome", this.nome);
        resultado.put("descricao", this.descricao);
        resultado.put("id", this.id);
        resultado.put("preco", this.preco);
        resultado.put("quantidade", this.quantidade);
        resultado.put("detalhes", this.detalhes);
        resultado.put("peso", this.peso);
        resultado.put("imagem", this.imagem);
        if (completo) {
            resultado.put("animal", new JSONObject(this.animal.toJSON()));
            resultado.put("categoria", new JSONObject(this.categoria.toJSON()));
        } else {
            resultado.put("id_animal", this.id_animal);
            resultado.put("id_categoria", this.id_categoria);
        }

        return resultado.toString();
    }

}
