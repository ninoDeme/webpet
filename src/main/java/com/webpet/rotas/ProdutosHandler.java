package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.ProdutoSimples;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class ProdutosHandler extends Rota {

    public ProdutosHandler(Connection conexao) {
        super("ProdutosHandler", conexao);
    }

    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        try {
            // Declarando novo Array dinámico para salvar os produtos
            ArrayList<ProdutoSimples> produtos = new ArrayList<ProdutoSimples>();
            
            // Executando sql para retornar todos os produtos e salvando o resultado na variável "resultados"
            String sql = "select id_produto, nome, descricao, preco, quantidade, imagem from produto";
            // Executando sql para retornar todos os produtos e salvando o resultado na
            // variável "resultados"
            if (query.get("categoria") != null) {
                sql += " where id_categoria = " + query.get("categoria");
                if (query.get("animal") != null) {
                    sql += " and id_animal = " + query.get("animal");
                }
            }
            if (query.get("animal") != null) {
                sql += " where id_animal = " + query.get("animal");
            }

            if (query.get("limit") != null) {
                sql += " limit " + query.get("limit");
            }

            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            // Iterando por todos os produtos, inicializando o objeto produto e salvando no
            // array produtos
            for (boolean condicao = resultados.next(); condicao; condicao = resultados.next()) {
                String nome = resultados.getString("nome");
                int id = resultados.getInt("id_produto");
                String descricao = resultados.getString("descricao");
                double preco = resultados.getDouble("preco");
                int quantidade = resultados.getInt("quantidade");
                String imagem = resultados.getString("imagem");

                produtos.add(new ProdutoSimples(nome, descricao, preco, id, quantidade, imagem));
            }

            // Criando JSON para retornar na resposta
            response = "{\"resultado\": [";
            for (int i = 0; i < produtos.size(); i++) {
                response += produtos.get(i).toJSON();
                if (i < produtos.size() - 1) {
                    response += ",";
                }
            }
            response += "]}";

            return new RespostaHttp(response).tipo("application/json");
        } catch (SQLException e) {
            response = "Falha ao buscar os produtos";
            e.printStackTrace();
            return new RespostaHttp(response).code(500);
        }

    }
}
