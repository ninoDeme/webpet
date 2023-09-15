package com.webpet.classes;

import org.json.JSONObject;

public class ProdutoSimples {
  int id;
  String nome;
  String descricao;
  double preco;
  int quantidade;
  String imagem;

  public ProdutoSimples(String nome, String descricao, double preco, int id, int quantidade, String imagem) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.id = id;
    this.quantidade = quantidade;
    this.imagem = imagem;
  }

  public ProdutoSimples(String nome, String descricao, double preco, int quantidade, String imagem) {
    this.nome = nome;
    this.descricao = descricao;
    this.preco = preco;
    this.quantidade = quantidade;
    this.imagem = imagem;
  }

  public String toJSON() {
    JSONObject res = new JSONObject();

    res.put("nome", this.nome);
    res.put("descricao", this.descricao);
    res.put("id", this.id);
    res.put("preco", this.preco);
    res.put("imagem", this.imagem);
    res.put("quantidade", this.quantidade);

    return res.toString();
  }

}
