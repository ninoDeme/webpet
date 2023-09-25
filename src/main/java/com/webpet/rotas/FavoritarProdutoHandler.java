package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
import com.webpet.classes.Usuario;


public class FavoritarProdutoHandler extends Rota {

    public FavoritarProdutoHandler(Connection conexao) {
        super(conexao);
    }

    // Declare um método "get" ou "post" dependendo do tipo de requisição
    @Override
    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        Usuario user;
        try {
            user = this.Auth(pedido);
        } catch (Throwable e) {
            return new RespostaHttp("Erro: Não Autorizado").code(401);
        }

        String response;
        try {
            
            String sql = "INSERT INTO favoritos_usuario(id_produto,id_usuario,tipo)Values(?,?,'')";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            
            ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(query.get("id")));
            ps.setInt(2, user.id);
            ps.execute();
        
          return new RespostaHttp("Favoritado com sucesso");
           
        } catch (SQLException e) {
            response = "Falha ao inserir favoritos";
            e.printStackTrace();
            return new RespostaHttp(response).code(500);
        }
    }
}
