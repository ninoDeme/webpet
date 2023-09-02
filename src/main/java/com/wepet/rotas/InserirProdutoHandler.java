package com.wepet.rotas;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class InserirProdutoHandler implements HttpHandler {

    Connection conexao = null;

    public InserirProdutoHandler(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void handle(HttpExchange pedido) throws IOException {
        System.out.println((this.getClass().getSimpleName()) + ": Requisição recebida - " + pedido.getRequestURI().toString());

        String response = null;
        int codigo;
        try {
            String[] variaveis = pedido.getRequestURI().toString().split("\\&");

            String nome = null;
            String descricao = null;
            String preco = null;

            for (int i = 1; i < variaveis.length; i++) {
                String[] var = variaveis[i].split("=");
                String chave = var[0];
                String valor = var[1];

                if (chave.equalsIgnoreCase("nome")) {
                    nome = valor;
                } else if (chave.equalsIgnoreCase("descricao")) {
                    descricao = valor;
                } else if (chave.equalsIgnoreCase("preco")) {
                    preco = valor;
                }
            }
            if (nome == null || descricao == null || preco == null) {
                throw new Error();
            }
            String sql = "insert into produtos (nome, descricao, preco) values ('" + nome + "', '" + descricao + "', "
                    + preco + ")";
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.execute();
            response = "Inserido produto com sucesso";
            codigo = 200;

        } catch (SQLException e) {
            response = "Falha ao inserir produto";
            codigo = 500;
            e.printStackTrace();
        }

        pedido.sendResponseHeaders(codigo, response.length());
        OutputStream os = pedido.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
