package com.wepet.classes;

public class Pets{
    int id;
    String nome;
    

    public Pets(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String toJSON() {
        String resultado = "{";

        resultado = resultado + "\"nome\": \"" + this.nome + "\",";
        resultado = resultado + "\"id\": " + Integer.toString(this.id) + "}";

        return resultado;
    }

}
