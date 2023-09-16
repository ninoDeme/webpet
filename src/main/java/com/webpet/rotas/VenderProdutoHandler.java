package com.webpet.rotas;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.webpet.classes.RespostaHttp;
import com.webpet.classes.Rota;
import com.webpet.classes.Usuario;

public class VenderProdutoHandler extends Rota {
    public VenderProdutoHandler(Connection conexao) {
        super("VenderProdutoHandler", conexao);
    }

    @Override
    public RespostaHttp post(String body, HttpExchange pedido) {
    Usuario venda = this.Auth(pedido);

        try {
            

            JSONObject b = new JSONObject(body);
            venda.id = b.getInt("id"); // Corrigido o método de conversão
            venda.id_venda = b.getInt("id_venda");
            venda.id_usuario = b.getInt("id_usuario");
            venda.data_horario = b.getString("data_horario");
            venda.tempo_entrega = b.getString("tempo_entrega");
            venda.cep = b.getString("cep");
          
        } catch (Throwable e) {
            e.printStackTrace();
            return new RespostaHttp().code(400).send("Body Inválido"); // Corrigido "Body Invalido"
        }

        try {
            String sql = "INSERT INTO venda (id, id_produto, id_usuario,data_horario,tempo_entrega,cep) values (?, ?, ?,?,?,?)";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            
            ps.setInt(1, venda.id); // Corrigido
            ps.setInt(2, venda.id_venda);
            ps.setInt(3, venda.id_usuario);
            ps.setString(3, venda.data_horario);
            ps.setString(3, venda.tempo_entrega);
            ps.setString(3, venda.cep);

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return new RespostaHttp().code(500).send("Não foi possível finalizar a Compra!");
        }

        return new RespostaHttp("Aompra  realizada Com Sucesso!!");
    }
}
