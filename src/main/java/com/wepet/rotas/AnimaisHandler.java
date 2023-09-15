package com.wepet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.wepet.classes.Animal;
import com.wepet.classes.RespostaHttp;
import com.wepet.classes.Rota;

public class AnimaisHandler extends Rota {

    public AnimaisHandler(Connection conexao) {
        super("AnimaisHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição 
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        int codigo;
        try {
            // Declarando novo Array dinámico para salvar os produtos
            ArrayList<Animal> animal = new ArrayList<Animal>();
            
            // Executando sql para retornar todos os produtos e salvando o resultado na variável "resultados"
            String sql = "select * from animal";
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

            codigo = 200;

        } catch (SQLException e) {
            response = "Falha ao inserir produto";
            codigo = 500;
            e.printStackTrace();
        }

        return new RespostaHttp(codigo, response, "application/json");
    }
}
