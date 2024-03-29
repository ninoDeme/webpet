package com.webpet.classes;

import org.json.JSONObject;

public class ProdutoSimples {
  int id;
  String nome;
  double preco;
  int quantidade;
  String imagem;

  public ProdutoSimples(String nome, double preco, int id, int quantidade, String imagem) {
    this.nome = nome;
    this.preco = preco;
    this.id = id;
    this.quantidade = quantidade;
    this.imagem = imagem;
  }

  public ProdutoSimples(String nome, double preco, int quantidade, String imagem) {
    this.nome = nome;
    this.preco = preco;
    this.quantidade = quantidade;
    this.imagem = imagem;
  }

  public String toJSON() {
    JSONObject res = new JSONObject();

    res.put("nome", this.nome);
    res.put("id", this.id);
    res.put("preco", this.preco);
    res.put("imagem", this.imagem);
    res.put("quantidade", this.quantidade);

    return res.toString();
  }

}
