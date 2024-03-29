package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
import com.webpet.classes.Usuario;

public class CadastrarHandler extends Rota {
    public CadastrarHandler(Connection conexao) {
        super(conexao);
    }

    @Override
    public RespostaHttp post(String body, HttpExchange exchange) {
        Usuario novo = new Usuario();

        try {
            JSONObject b = new JSONObject(body);
            novo.nome = b.getString("nome");
            novo.email = b.getString("email");
            novo.senha = b.getString("senha");
            novo.telefone = b.getString("telefone");
            if (novo.email.equals("") || novo.nome.equals("") || novo.senha.equals("") || novo.telefone.equals("")) {
                throw new Error();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return new RespostaHttp().code(400).send("Body Invalido");
        }

        try {
            String sql = "INSERT INTO usuario (nome, senha, email, telefone) values (?, ?, ?, ?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, novo.nome);
            ps.setString(2, novo.senha);
            ps.setString(3, novo.email);
            ps.setString(4, novo.telefone);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaHttp().code(500).send("Não foi possível concluir o cadastro");
        }

        return new RespostaHttp("Usuario Cadastrado Com Sucesso");
    }

}
