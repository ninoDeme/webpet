package com.wepet.rotas;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.wepet.classes.Produto;

public class ProdutosHandler implements HttpHandler {

    Connection conexao = null;

    public ProdutosHandler(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void handle(HttpExchange pedido) throws IOException {
        System.out.println(this.getClass().getSimpleName() + ": Requisição recebida - " + pedido.getRequestURI().toString());

        String response = null;
        int codigo;
        try {
            ArrayList<Produto> produtos = new ArrayList<Produto>();
            String sql = "select * from produtos";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet resultados = ps.executeQuery();

            boolean condicao = true;
            while (condicao) {
                String nome = resultados.getString("nome");
                int id = resultados.getInt("id");
                String descricao = resultados.getString("descricao");
                double preco = resultados.getDouble("preco");

                produtos.add(new Produto(nome, descricao, preco, id));

                condicao = resultados.next();
            }

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

        byte[] bytes = response.getBytes();
        pedido.getResponseHeaders().set("content-type", "application/json");
        pedido.sendResponseHeaders(codigo, bytes.length);
        OutputStream os = pedido.getResponseBody();
        try {
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        os.close();

    }
}
