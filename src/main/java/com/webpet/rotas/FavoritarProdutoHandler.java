package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;

public class FavoritarProdutoHandler extends Rota {

    public FavoritarProdutoHandler(Connection conexao) {
        super("UnicoProdutoHandler", conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        String response;
        try {
            // this.Auth(pedido);
            // Usuario usuario = this.Auth(pedido);
            // Declarando novo Array dinámico para salvar os produtos

            // Executando sql para retornar todos os produtos e salvando o resultado na
            // variável "resultado
            // Iterando por todos os produtos, inicializando o objeto produto e salvando no
            // array produtos
            
            String sql = "INSERT INTO favoritos_usuario(id_produto,id_usuario,tipo)Values(?,?,'')";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            
            ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(query.get("id")));
            ps.setInt(2, Integer.parseInt(query.get("u")));
        
          return new RespostaHttp("Favoritado com sucesso");
           
        } catch (SQLException e) {
            response = "Falha ao inserir favoritos";
            e.printStackTrace();
            return new RespostaHttp(response).code(500);
        }
    }
}
