package com.wepet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.wepet.classes.Categoria;
import com.wepet.classes.RespostaHttp;
import com.wepet.classes.Rota;

public class CategoriasHandler extends Rota {

    public CategoriasHandler(Connection conexao) {
        super("CategoriasHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        int codigo;
        try {
            // Declarando novo Array dinámico para salvar os produtos
            ArrayList<Categoria> categorias = new ArrayList<Categoria>();

            // Executando sql para retornar todos os produtos e salvando o resultado na
            // variável "resultados"
            String sql = "select * from categoria";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            // Iterando por todos os produtos, inicializando o objeto produto e salvando no
            // array produtos
            for (boolean condicao = resultados.next(); condicao; condicao = resultados.next()) {
                String nome = resultados.getString("nome");
                int id = resultados.getInt("id_categoria");
                String imagem = resultados.getString("imagem");

                categorias.add(new Categoria(nome, imagem, id));
            }

            // Criando JSON para retornar na resposta
            response = "{\"resultado\": [";
            for (int i = 0; i < categorias.size(); i++) {
                response += categorias.get(i).toJSON();
                if (i < categorias.size() - 1) {
                    response += ",";
                }
            }
            response += "]}";

            codigo = 200;

        } catch (SQLException e) {
            response = "Falha ao inserir categoria";
            codigo = 500;
            e.printStackTrace();
        }

        return new RespostaHttp(response).tipo("application/json");

    }
}
