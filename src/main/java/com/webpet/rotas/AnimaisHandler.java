package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.Animal;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class AnimaisHandler extends Rota {

    public AnimaisHandler(Connection conexao) {
        super("AnimaisHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição 
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        try {
            // Declarando novo Array dinámico para salvar os produtos
            ArrayList<Animal> animal = new ArrayList<Animal>();
            
            // Executando sql para retornar todos os produtos e salvando o resultado na variável "resultados"
            String sql = "select * from animal";
            if (query.get("limit") != null) {
                sql += " order by random() limit " + query.get("limit");
            }
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            // Iterando por todos os produtos, inicializando o objeto produto e salvando no array produtos
            for (boolean condicao = resultados.next(); condicao ; condicao = resultados.next()) {
                String nome = resultados.getString("nome");
                int id_animal = resultados.getInt("id_animal");
                String descricao = resultados.getString("descricao");
                String imagem = resultados.getString("imagem");

                animal.add(new Animal(nome, descricao, imagem, id_animal));
            }

            // Criando JSON para retornar na resposta
            response = "{\"resultado\": [";
            for (int i = 0; i < animal.size(); i++) {
                response += animal.get(i).toJSON();
                if (i < animal.size() - 1) {
                    response += ",";
                }
            }
            response += "]}";

            return new RespostaHttp(response).tipo("application/json");
        } catch (SQLException e) {
            response = "Falha ao inserir produto";
            e.printStackTrace();
            return new RespostaHttp(response).code(500);
        }
    }
}
