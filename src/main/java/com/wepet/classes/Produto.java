package com.wepet.classes;

public class Produto {
    
    String nome;
    String descricao;
    double preco;
    int id;
    int quantidade;
    int id_categoria;
     int id_animal;
    String imagem;
    Double peso;
    String detalhes;

    public Produto(String nome, String descricao, double preco, int id, int quantidade, int id_categoria, int id_animal,String imagem,Double peso,String detalhes) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.id = id;
        this.quantidade=quantidade;
        this.id_categoria=id_categoria;
         this.id_animal=id_animal;
         this.imagem=imagem;                         
        this.peso=peso;
        this.detalhes=detalhes;   


    }

    public String toJSON() {
        String resultado = "{";

        resultado += "\"nome\": \"" + this.nome + "\",";
        resultado += "\"descricao\": \"" + this.descricao + "\",";
        resultado += "\"id\": " + Integer.toString(this.id) + ",";
        resultado += "\"preco\": " + Double.toString(this.preco);
        resultado += "\"quantidade\": " + Integer.toString(this.quantidade);
        resultado += "\"detalhes\": " + (this.detalhes);
        resultado += "\"peso\": " + Double.toString(this.peso);
        resultado += "\"imagem\": " +(this.imagem);
        resultado += "\"id_animal\": " + Integer.toString(this.id_animal);
        resultado += "\"id_categoria\": " + Integer.toString(this.id_categoria);
        
        resultado += "}";

        return resultado;
    }

}
