package com.wepet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.wepet.classes.Produto;
import com.wepet.classes.RespostaHttp;
import com.wepet.classes.Rota;

public class UnicoProdutoHandler extends Rota {

    public UnicoProdutoHandler(Connection conexao) {
        super("UnicoProdutoHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição 
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        int codigo;
        try {
            // Declarando novo Array dinámico para salvar os produtos
            
            // Executando sql para retornar todos os produtos e salvando o resultado na variável "resultados"
            String sql = "select * from produto where id_produto = " + query.get("id");
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            // Iterando por todos os produtos, inicializando o objeto produto e salvando no array produtos
             
                String nome = resultados.getString("nome");
                int id = resultados.getInt("id_produto");
                String descricao = resultados.getString("descricao");
                double preco = resultados.getDouble("preco");
             

            // Criando JSON para retornar na resposta
            response = "{\"resultado\": [";
            for (int i = 0; i < produtos.size(); i++) {
                response += produtos.get(i).toJSON();
                if (i < produtos.size() - 1) {
                    response += ",";
                }
            }
            response += "]}";

            codigo = 200;

        } catch (SQLException e) {
            response = "Falha ao inserir produto";
            codigo = 500;
            e.printStackTrace();
        }

        return new RespostaHttp(codigo, response, "application/json");
    }
}

