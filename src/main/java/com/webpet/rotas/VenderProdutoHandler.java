package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.AuthException;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
import com.webpet.classes.Usuario;
import com.webpet.classes.Venda;

public class VenderProdutoHandler extends Rota {
    public VenderProdutoHandler(Connection conexao) {
        super(conexao);
    }

    @Override
    public RespostaHttp post(String body, HttpExchange pedido) {
        Usuario usuario;
        try {
            usuario = this.Auth(pedido);
        } catch (AuthException e) {
            return e.getResposta();
        }

        Venda venda = new Venda();

        try {

            JSONObject b = new JSONObject(body);
            venda.id_produto = b.getInt("id_produto"); // Corrigido o método de conversão
            venda.id_usuario = usuario.id;
            venda.data_horario = b.getString("data_horario");
            venda.tempo_entrega = b.getString("tempo_entrega");
            venda.cep = b.getString("cep");

        } catch (Throwable e) {
            e.printStackTrace();
            return new RespostaHttp().code(400).send("Body Inválido"); // Corrigido "Body Invalido"
        }

        try {
            String sql = "INSERT INTO venda ( id_produto, id_usuario,data_horario,tempo_entrega,cep) values (?, ?,?,?,?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);

            ps.setInt(1, venda.id_produto); // Corrigido
            ps.setInt(2, venda.id_usuario);
            ps.setString(3, venda.data_horario);
            ps.setString(4, venda.tempo_entrega);
            ps.setString(5, venda.cep);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaHttp().code(500).send("Não foi possível finalizar a Compra!");
        }

        return new RespostaHttp("Compra  realizada Com Sucesso!!");
    }
}
