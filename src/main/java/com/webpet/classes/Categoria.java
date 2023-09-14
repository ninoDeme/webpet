package com.webpet.classes;

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
        String resultado = "{";

        resultado += "\"nome\": \"" + this.nome + "\",";
        resultado += "\"imagem\": \"" + this.imagem + "\",";
        resultado += "\"id\": " + Integer.toString(this.id);
    
        
        resultado += "}";

        return resultado;
    }

}

    

