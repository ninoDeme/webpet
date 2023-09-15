package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.Produto;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class UnicoProdutoHandler extends Rota {

    public UnicoProdutoHandler(Connection conexao) {
        super("UnicoProdutoHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        try {
            // Declarando novo Array dinámico para salvar os produtos

            // Executando sql para retornar todos os produtos e salvando o resultado na
            // variável "resultados"
            String sql = "select * from produto where id_produto = " + query.get("id");
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            // Iterando por todos os produtos, inicializando o objeto produto e salvando no
            // array produtos
            String nome = resultados.getString("nome");
            String descricao = resultados.getString("descricao");
            double preco = resultados.getDouble("preco");
            int id = resultados.getInt("id_produto");
            int quantidade = resultados.getInt("quantidade");
            int id_categoria = resultados.getInt("id_categoria");
            int id_animal = resultados.getInt("id_animal");
            String imagem = resultados.getString("imagem");
            Double peso = resultados.getDouble("peso");
            String detalhes = resultados.getString("detalhes");


            Produto unicoProduto = new Produto(nome, descricao, preco, id, quantidade, id_categoria, id_animal, imagem, peso,detalhes);

            // Criando JSON para retornar na resposta
            response = "{\"resultado\": [";

            response += unicoProduto.toJSON();
            

            response += "]}";

            return new RespostaHttp(response).tipo("application/json");
        } catch (SQLException e) {
            response = "Falha ao inserir produto";
            e.printStackTrace();
            return new RespostaHttp(response).code(500);
        }

    }
}
