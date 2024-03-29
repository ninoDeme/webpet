package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
import com.webpet.classes.Usuario;

public class LoginHandler extends Rota {
    
    public LoginHandler(Connection conexao) {
        super(conexao);
    }

    public RespostaHttp get(Map<String, String> query, HttpExchange pedido) {
        try {
            Usuario user = this.Auth(pedido);
            return new RespostaHttp(user.toJSON());
        } catch (Throwable e) {
            return new RespostaHttp("Erro: Não Autorizado").code(401);
        }

    }

    @Override
    public RespostaHttp post(String body, HttpExchange exchange) {
        Usuario user = new Usuario();
        
        try {
            JSONObject b = new JSONObject(body);
            user.email = b.getString("email");
            user.senha = b.getString("senha");
        } catch (JSONException e) {
            e.printStackTrace();
            return new RespostaHttp().code(400).send("Body Invalido");
        }
        
        try {
            String sql = "SELECT id_usuario, nome, telefone from usuario where email = ? and senha = ?";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, user.email);
            ps.setString(2, user.senha);
            ResultSet resultado = ps.executeQuery();
            if (!resultado.next()) {
                throw new Error();
            }
            user.id = resultado.getInt("id_usuario");
            user.nome = resultado.getString("nome");
            user.telefone = resultado.getString("telefone");

            Algorithm algorithm = Algorithm.HMAC256(Rota.secret);
            user.senha = null;
            String token = JWT.create().withIssuer("WebPet").withPayload(user.toJSON()).sign(algorithm);

            return new RespostaHttp(user.toJSON()).setHeader("Set-Cookie", "Auth=" + token );
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaHttp().code(401).send("Erro: Usuario ou Senha Incorretos");
        }
    }

}
